package org.cgn.simulation.base.init;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.cgn.simulation.base.config.WebAutoConfiguration.INITIALIZE_PATH;

@RestController
public final class InitializeDispatcherServletController {

    @GetMapping(INITIALIZE_PATH)
    public void initializeDispatcherServlet() {
        System.out.println("Initialized the dispatcherServlet to improve the first response time of the interface...");
    }


}
