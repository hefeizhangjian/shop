package com.zj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 张建 119855181@qq.com
 * @version V1.0
 * @Package com.zj.config
 * @date 2020/4/20 21:12
 * @Copyright © 安徽华泓信息技术有限公司
 * @description: Swagger2接口文档插件配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 原路径: http://localhost:8088/swagger-ui.html
     *     换肤:http://localhost:8088/doc.html
    */
    @Bean
    public Docket createRestApi(){
                        //指定API类型为swagger2
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                //定义API文档的汇总信息
                .apiInfo(apiInfo())
                //指定接口所在的包
                .select().apis(RequestHandlerSelectors.basePackage("com.zj.controller"))
                //所有controller
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("电商平台接口API")
                .description("专为电商平台提供的api接口文档")
                //联系人信息
                .contact(new Contact("张建","http://www.sqzgvip.cn","119855181@qq.com"))
                .version("1.0.1")
                .termsOfServiceUrl("http://www.sqzgvip.cn")
                .build();
    }

}
