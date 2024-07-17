package org.cgn.simulation.controller;

import lombok.RequiredArgsConstructor;
import org.cgn.simulation.base.log.annotation.ILog;
import org.cgn.simulation.base.web.Result;
import org.cgn.simulation.base.web.Results;
import org.cgn.simulation.dto.req.RunBenchmarkReqDTO;
import org.cgn.simulation.dto.resp.ConfigFileListRespDTO;
import org.cgn.simulation.service.SimulationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SimulationController {
    private final SimulationService simulationService;

    @GetMapping("/search")
    public Result<ConfigFileListRespDTO> pageListQuery(){
        return Results.success(simulationService.pageListQuery());
    }

    @ILog
    @PostMapping("/run/{fileName}")
    public Result<String> startSimulation(@PathVariable String fileName) {
        return Results.success(simulationService.runConfig(fileName));
    }

    @GetMapping("/isCompleted")
    public Result<List<String>> isCompleted(@RequestParam List<String> fileNames) {
        return Results.success(simulationService.isCompleted(fileNames));
    }

    @ILog
    @PostMapping("/stop/{fileName}")
    public Result<Object> stopSimulation(@PathVariable String fileName) {
        return Results.success(simulationService.stopSimulation(fileName));
    }

    @ILog
    @PostMapping("/create")
    public Result<RunBenchmarkReqDTO> createConfigFile(@RequestBody RunBenchmarkReqDTO runBenchmarkReqDTO) {
        return Results.success(simulationService.createConfigFile(runBenchmarkReqDTO));
    }

    @ILog
    @PostMapping("/update")
    public Result<Object> updateConfigFile(@RequestBody RunBenchmarkReqDTO runBenchmarkReqDTO) {
        return Results.success(simulationService.updateConfigFile(runBenchmarkReqDTO));
    }

    @ILog
    @PostMapping("/delete/{fileName}")
    public Result<String> deleteConfigFile(@PathVariable String fileName) {
        return Results.success(simulationService.deleteConfigFile(fileName));
    }
}
