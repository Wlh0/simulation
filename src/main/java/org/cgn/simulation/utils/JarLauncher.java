package org.cgn.simulation.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JarLauncher {
    private final Map<String, Process> runningProcess  = new ConcurrentHashMap<>();

    public String launchJar(String configFilePath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java", "-jar", "src/main/resource/xxx.jar", configFilePath
        );
        processBuilder.inheritIO();
        Process start = processBuilder.start();
        String processKey = String.valueOf(start.pid());
        runningProcess.put(processKey, start);
        return processKey;
    }

    public String getProcessId(String processKey){
        Process process = runningProcess.get(processKey);
        if (process != null) {
            return String.valueOf(process.pid());
        }
        return null;
    }

    public void stopProcess(String processKey) {
        Process process = runningProcess.get(processKey);
        if (process != null) {
            process.destroy();
            runningProcess.remove(processKey);
        }
    }
}
