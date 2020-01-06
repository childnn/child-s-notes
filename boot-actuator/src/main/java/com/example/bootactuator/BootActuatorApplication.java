package com.example.bootactuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.beans.BeansEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * http://localhost:9000/actuator
 */
@SpringBootApplication
public class BootActuatorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BootActuatorApplication.class, args);
        BeansEndpoint endpoint = ctx.getBean(BeansEndpoint.class);
        BeansEndpoint.ApplicationBeans beans = endpoint.beans();
        Map<String, BeansEndpoint.ContextBeans> contexts = beans.getContexts();
        contexts.forEach((k, v) -> {
            System.err.println(k);
            Map<String, BeansEndpoint.BeanDescriptor> bs = v.getBeans();
            bs.forEach((k1, v1) -> System.err.println(k1 + ": " + v1.getType()));
        });
    }

}
