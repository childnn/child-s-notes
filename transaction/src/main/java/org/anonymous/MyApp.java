package org.anonymous;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * GET多参数这种方式也是可以的
 * @GetMapping("/car/{carId}/user/{userId}")
 * TblCar findById(@PathVariable(value="carId") Long carId,@PathVariable(value="userId") Long userId);
 *
 * @author MiaoOne
 * @since 2019/12/15 20:18
 * @see AnnotationConfigServletWebServerApplicationContext  -- web 环境下的 ctx.
 * @see DefaultSingletonBeanRegistry#registerSingleton(java.lang.String, java.lang.Object)
 * @see ClassPathBeanDefinitionScanner
 * .@see BeanDefinitionLoader
 * @see BeanNameGenerator
 * .@see ClassPathBeanDefinitionScanner#doScan(java.lang.String...)
 * @see ClassPathScanningCandidateComponentProvider#findCandidateComponents(java.lang.String)
 * @see PropertySourcesPropertyResolver
 * @see org.springframework.web.context.support.ServletContextResourcePatternResolver
 * @see org.springframework.util.AntPathMatcher
 * @see ScannedGenericBeanDefinition
 * @see org.springframework.core.type.StandardAnnotationMetadata
 * .@see AnnotationsScanner#scan(java.lang.Object, java.lang.reflect.AnnotatedElement, org.springframework.core.annotation.MergedAnnotations.SearchStrategy, org.springframework.core.annotation.AnnotationsProcessor)
 * @see org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition
 * @see org.springframework.beans.factory.config.BeanDefinitionHolder
 * @see ServletWebServerApplicationContext#refresh()
 * @see AbstractApplicationContext#refresh()
 * @see org.springframework.web.context.support.StandardServletEnvironment
 */
//@EnableWebMvc
@MapperScan("org.anonymous.dao")
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MyApp.class, args);
        String name = ctx.getApplicationName();
        System.out.println("name = " + name);
    }
}
