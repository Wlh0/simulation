package org.cgn.simulation.controller;

import org.cgn.simulation.base.designpattern.JarLauncherFactory;
import org.cgn.simulation.base.web.Result;
import org.cgn.simulation.base.web.Results;
import org.cgn.simulation.utils.JarLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public class SimulationController {
    private final JarLauncher jarLauncher = JarLauncherFactory.getInstance();

    @PostMapping("/run")
    public Result<Void> startSimulation(@RequestParam String configFilename) {
        try {
            String processKey = jarLauncher.launchJar(configFilename);
//            TODO: 返回的实体类需要定义一下
            return Results.success();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/stop")
    public String stopSimulation(@RequestParam String processKey) {
        jarLauncher.stopProcess(processKey);
        return "停止成功";
    }
}
