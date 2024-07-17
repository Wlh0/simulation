package org.cgn.simulation.service;

import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.cgn.simulation.dto.resp.ConfigFileListRespDTO;

import java.util.List;

public interface SimulationService {

    String runConfig(String fileName);

    ConfigFileListRespDTO pageListQuery();

    RunBenchmarkReqDTO createConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO);

    Object stopSimulation(String fileName);

    Object updateConfigFile(RunBenchmarkReqDTO runBenchmarkReqDTO);

    String deleteConfigFile(String fileName);

    List<String> isCompleted(List<String> fileNames);
}
