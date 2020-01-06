package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2020/1/5 11:40
 */
@Configuration
public class JsonConvertConfig implements WebMvcConfigurer {

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
////        converters.forEach(System.err::println);
//        converters.clear();
//        converters.add(httpMessageConverter());
//        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
//    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        // 与注册顺序有关?
        converters.add(httpMessageConverter());
        // 如果只注册 json, 不注册 string, 则 不支持 Content-Type: text/plain.
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @Bean
    public MappingJackson2HttpMessageConverter httpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
