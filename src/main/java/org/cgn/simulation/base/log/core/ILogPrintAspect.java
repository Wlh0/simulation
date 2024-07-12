package org.cgn.simulation.base.log.core;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.cgn.simulation.base.log.annotation.ILog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
public class ILogPrintAspect {


    @Around("@within(org.cgn.simulation.base.log.annotation.ILog) || @annotation(org.cgn.simulation.base.log.annotation.ILog)")
    public Object printMLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = SystemClock.now();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
        String beginTime = DateUtil.now();
        Object result = null;

        try {
            result = joinPoint.proceed();
        }finally {
            Method targetMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getMethod().getParameterTypes());
            ILog logAnnotation = Optional.ofNullable(targetMethod.getAnnotation(ILog.class)).orElse(joinPoint.getTarget().getClass().getAnnotation(ILog.class));
            if (logAnnotation != null) {
                ILogPrintDTO logPrintDTO = new ILogPrintDTO();
                logPrintDTO.setBeginTime(beginTime);
                if (logAnnotation.input()) {
                    logPrintDTO.setInputParams(buldInput(joinPoint));
                }
                if (logAnnotation.output()){
                    logPrintDTO.setOutputParams(result);
                }
                String methodType = "", requestURI = "";
                try {
                    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                    assert requestAttributes != null;
                    methodType = requestAttributes.getRequest().getMethod();
                    requestURI = requestAttributes.getRequest().getRequestURI();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("[{}] {}, executeTime: {}ms, info:{}", methodType, requestURI, SystemClock.now() - startTime, JSON.toJSONString(logPrintDTO));
            }
        }
        return result;
    }

    private Object[] buldInput(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] printArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if ((args[i] instanceof HttpServletRequest) || args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof byte[]) {
                printArgs[i] = "byte array";
            } else if (args[i] instanceof MultipartFile) {
                printArgs[i] = "file";
            }else {
                printArgs[i] = args[i];
            }
        }
        return printArgs;
    }
}
