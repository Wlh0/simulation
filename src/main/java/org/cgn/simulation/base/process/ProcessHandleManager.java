package org.cgn.simulation.base.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 执行进程将process直接放入concurrentMap中，直接返回process给前端，当需要执行结束功能的时候，可以直接从map中取出Manager类，直接销毁进程
 * 全局一个，所以可以使用单例模式去获取Manager，管控所有启动的进程
 */
public final class ProcessHandleManager {
    private static final Map<String, Process> MANAGER = new ConcurrentHashMap<>();

    /**
     * 存储线程还是进程，线程的话是不是可以是使用ThreadLocal
     *
     * @param configFile
     */
    public void runProcess(String configFile) {
        boolean flag = false;
        Thread thread = new Thread(() -> {
            Process process = null;
            try {
                //TODO:具体脚本执行拼接再看
//                process = new ProcessBuilder("sh", "benchmark.sh", "-cf", "configFile").start();
//                process = new ProcessBuilder("sh" , "benchmark.sh", "-cf", ConfigFilePath.CONFIG_FILE_PATH_PREFIX + configFile + ".yml").start();
                process = new ProcessBuilder("ping", "baidu.com").start();
                MANAGER.put(configFile, process);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                process.waitFor();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            stopProcess(configFile);
            //TODO:回调通知前端
        });
        thread.start();

    }

    public void stopProcess(String configFile) {
        Process process = MANAGER.get(configFile);
        if (process != null) {
            process.destroy();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MANAGER.remove(configFile);
            System.out.println("Process terminated.");
        }
    }

    public List<String> check(List<String> fileNames) {
        List<String> processes = new ArrayList<>();
        for (String fileName : fileNames) {
            Process process = MANAGER.get(fileName);
            if (!process.isAlive()) {
                processes.add(fileName);
                stopProcess(fileName);
            }
        }
        return processes;
    }

    public static void main(String[] args) throws InterruptedException {
//        new AnnotationConfigApplicationContext()
        ProcessHandleManager p = new ProcessHandleManager();
        p.runProcess("1");
        Thread.sleep(2000);
        p.stopProcess("1");
    }
}
