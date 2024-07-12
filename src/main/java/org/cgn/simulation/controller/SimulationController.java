package org.cgn.simulation.controller;

import lombok.RequiredArgsConstructor;
import org.cgn.simulation.base.log.annotation.ILog;
import org.cgn.simulation.base.web.Result;
import org.cgn.simulation.base.web.Results;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.cgn.simulation.service.SimulationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @GetMapping("/search")
    public Result<String> pageListQuery(){
        return Results.success(simulationService.pageListQuery());
    }

    @ILog
    @PostMapping("/run")
    public Result<String> startSimulation(@RequestBody String configFilename) {
        return Results.success(simulationService.runConfig(configFilename));
    }

    @ILog
    @PostMapping("/stop")
    public Result<Object> stopSimulation(@RequestParam String processKey) {
        return Results.success(simulationService.stopSimulation(processKey));
    }

    @ILog
    @PostMapping("create")
    public Result<Object> createConfigFile(@RequestBody RunBenchmarkReqDTO runBenchmarkReqDTO) {
        return Results.success(simulationService.createConfigFile(runBenchmarkReqDTO));
    }

    @ILog
    @PostMapping("/update")
    public Result<Object> updateConfigFile(@RequestBody RunBenchmarkReqDTO runBenchmarkReqDTO) {
        return Results.success(simulationService.updateConfigFile(runBenchmarkReqDTO));
    }

    @ILog
    @DeleteMapping("/delete")
    public Result<Object> deleteConfigFile(@RequestParam String configFileName) {
        return Results.success(simulationService.deleteConfigFile(configFileName));
    }
}
