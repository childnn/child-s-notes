package org.anonymous.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/11/6 15:30
 */
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SwaggerApplication.class, args);
        String[] beans = ctx.getBeanDefinitionNames();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(beans));
        list.forEach(System.err::println);

    }
}
