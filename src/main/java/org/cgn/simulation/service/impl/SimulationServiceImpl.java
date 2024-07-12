package org.cgn.simulation.service.impl;

import lombok.RequiredArgsConstructor;
import org.cgn.simulation.base.designpattern.JarLauncherFactory;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.cgn.simulation.service.SimulationService;
import org.cgn.simulation.utils.JarLauncher;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {

    private final JarLauncher jarLauncher = JarLauncherFactory.getInstance();

    @Override
    public String runConfig(String configFilename) {
        String processKey;
        try {
            processKey = jarLauncher.launchJar(configFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return processKey;
    }

    @Override
    public String pageListQuery() {
        return null;
    }

    /**
     * 创建单个config文件，持久化到本地
     * 只有三个文件，但是每个配置之间需要
     * @param runBenchmarkReqDTO
     * @return
     */
    @Override
    public Object createConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO) {

        return null;
    }

    @Override
    public Object stopSimulation(String processKey) {
        return null;
    }

    @Override
    public Object updateConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO) {
        return null;
    }

    @Override
    public Object deleteConfigFile(String configFileName) {
        return null;
    }
}
