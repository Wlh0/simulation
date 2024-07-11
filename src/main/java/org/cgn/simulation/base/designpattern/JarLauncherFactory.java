package org.cgn.simulation.base.designpattern;

import org.cgn.simulation.utils.JarLauncher;

public class JarLauncherFactory {
    public static JarLauncher getInstance(){
        JarLauncher instance = Singleton.get("JAR_LAUNCHER");
        if (instance == null) {
            instance = new JarLauncher();
            Singleton.put(instance);
        }
        return instance;
    }
}
