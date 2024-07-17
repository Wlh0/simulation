package org.cgn.simulation.service.impl;

import lombok.RequiredArgsConstructor;
import org.cgn.simulation.base.ApplicationContextHolder;
import org.cgn.simulation.base.constant.ConfigFilePath;
import org.cgn.simulation.base.designpattern.chain.AbstractChainContext;
import org.cgn.simulation.base.designpattern.singleton.ProcessHandleManagerFactory;
import org.cgn.simulation.base.process.ProcessHandleManager;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.cgn.simulation.dto.resp.ConfigFileListRespDTO;
import org.cgn.simulation.service.SimulationService;
import org.cgn.simulation.utils.YMLUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService, CommandLineRunner {

    private final ProcessHandleManager processHandleManager = ProcessHandleManagerFactory.getInstance();

    private final AbstractChainContext<RunBenchmarkReqDTO> runBenchmarkReqDTOAbstractChainContext;

    private SimulationService simulationService;
    @Override
    public String runConfig(String fileName) {
        processHandleManager.runProcess(fileName);
        return "启动成功";
    }

    @Override
    public ConfigFileListRespDTO pageListQuery() {
        List<RunBenchmarkReqDTO> list = new ArrayList<>();
        //        TODO：需要一个全局的静态变量文件夹前缀
        File file = new File(ConfigFilePath.CONFIG_FILE_PATH_PREFIX);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getAbsolutePath().endsWith(".yml")) {
                    return true;
                }
                return false;
            }
        });
        for (File config : files) {
            try {
                RunBenchmarkReqDTO runBenchmarkReqDTO = YMLUtil.YMLToJson(config);
                list.add(runBenchmarkReqDTO);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return ConfigFileListRespDTO.builder()
                .runBenchmarkReqDTOS(list)
                .build();
    }


//    public
    /**
     * 创建单个config文件，持久化到本地
     * 需要返回回去的肯定是状态，不嫩仅返回一个实体类，但是已经封装了successCode
     * 全局异常类
     * @param runBenchmarkReqDTO
     * @return
     */
    @Override
    public RunBenchmarkReqDTO createConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO) {
        // 对数据进行检查
        try {
            YMLUtil.jsonToYML(runBenchmarkReqDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return runBenchmarkReqDTO;
    }

    @Override
    public String stopSimulation(String fileName) {
        processHandleManager.stopProcess(fileName);
        return "关闭成功";
    }

    /**
     * 覆盖即修改
     * @param runBenchmarkReqDTO
     * @return
     */
    @Override
    public Object updateConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO) {
        try {
            YMLUtil.jsonToYML(runBenchmarkReqDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return runBenchmarkReqDTO;
    }

    @Override
    public String deleteConfigFile(String fileName) {
        File file = new File(ConfigFilePath.CONFIG_FILE_PATH_PREFIX + fileName + ".yml");
        if (file.delete()) {
            return fileName + "删除成功";
        }else {
            return "删除失败";
        }
    }

    @Override
    public List<String> isCompleted(List<String> fileNames) {
        List<String> check = processHandleManager.check(fileNames);
        return check;
    }

    @Override
    public void run(String... args) throws Exception {
        simulationService = ApplicationContextHolder.getBean(SimulationService.class);
    }
}
