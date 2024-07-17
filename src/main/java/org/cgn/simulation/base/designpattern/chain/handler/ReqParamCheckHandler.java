package org.cgn.simulation.base.designpattern.chain.handler;

import cn.hutool.core.util.StrUtil;
import org.cgn.simulation.base.designpattern.chain.filter.AbstractChainHandlerFilter;
import org.cgn.simulation.base.web.exception.ClientException;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;

public class ReqParamCheckHandler implements AbstractChainHandlerFilter<RunBenchmarkReqDTO> {
    @Override
    public void handler(RunBenchmarkReqDTO requestParam) {
        if (StrUtil.isBlank(requestParam.getFileName())) {
            throw new ClientException("配置文件名不能为空");
        }
        if (requestParam.getDevice() == 0) {
            throw new ClientException("");
        }
        if (requestParam.getSensor() == 0){
            throw new ClientException("");
        }
        if (requestParam.getTimeStamp() == 0) {
            throw new ClientException("");
        }
    }



    @Override
    public int getOrder() {
        return 0;
    }
}
