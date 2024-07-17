package org.cgn.simulation.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 肯定是要异步执行的
 */
public class JarLauncherUtil {

    private Process process;

    /**
     * 入参肯定需要有一个configFile
     * 两种执行方式：
     * 1. 直接java -jar xxx.jar -cf configFile.yml
     *    需要修改benchmark将加载的参数修改为实际加载的文件
     * 2. 执行sh脚本，可以指定配置文件
     */
    public void startProcess() {
        Thread processThread = new Thread(() -> {
            try {
//                process = new ProcessBuilder("java", "-jar", "xxx.jar", "-cf", "config.yml").start();
                process = new ProcessBuilder("ifconfig").start();
//                process = new ProcessBuilder("sh", "your_script.sh", "-cf", "configFile").start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                int exitCode = process.waitFor();
                System.out.println("process exited");
                stopProcess();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                public void run() {
//                    //TODO: 进程结束，需要将数据进行返回前端，修改运行状态
//                    System.out.println("回调函数执行");
//                }
//            });
        });
        processThread.start();
    }

    public void stopProcess() {
        if (process != null) {
            process.destroy();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Process terminated.");
        }
    }

    /**
     * 这个是需要每次调用都创建一个对象，是不是需要交给容器，使用工厂模式进行创建bean，再将bean放入map中，用于启动和销毁
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JarLauncherUtil manager = new JarLauncherUtil();
        manager.startProcess();

        // 模拟一些操作
//        Thread.sleep(5000);
//
//        // 终止进程
//        manager.stopProcess();
    }
}
