package org.cgn.simulation.base.designpattern.singleton;

import org.cgn.simulation.base.process.ProcessHandleManager;

public class ProcessHandleManagerFactory {
    public static ProcessHandleManager getInstance(){
        ProcessHandleManager instance = Singleton.get("PROCESS_HANDLE_MANAGER");
        if (instance == null) {
            instance = new ProcessHandleManager();
            Singleton.put(instance);
        }
        return instance;
    }
}
