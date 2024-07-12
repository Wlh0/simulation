package org.cgn.simulation.service;

import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;

public interface SimulationService {

    String runConfig(String configFilename);

    String pageListQuery();

    Object createConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO);

    Object stopSimulation(String processKey);

    Object updateConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO);

    Object deleteConfigFile(String configFileName);
}
