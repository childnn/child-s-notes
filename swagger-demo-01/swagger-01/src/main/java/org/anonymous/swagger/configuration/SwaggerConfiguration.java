package org.anonymous.swagger.configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @see EnableSwagger2 开启 Swagger 注解.
 * controller
 * @see springfox.documentation.annotations.ApiIgnore 屏蔽显示 method, type, parameter.
 * @see io.swagger.annotations.Api controller
 * @see ApiOperation
 * @see io.swagger.annotations.ApiParam
 * @see io.swagger.annotations.ApiImplicitParam
 * @see io.swagger.annotations.ApiImplicitParams
 * @see io.swagger.annotations.ApiModel
 * @see io.swagger.annotations.ApiModelProperty
 * @see io.swagger.annotations.ApiResponse
 * @see io.swagger.annotations.ApiResponses
 * @since 2019/11/6 15:37
 * <p>
 * 配置类:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger和SpringBoot整合") // 大标题.
                .description("Swagger的API文档") // 大标题下的描述.
                .version("2.0") // 大标题右上角的版本号.
                .build();
    }


}
