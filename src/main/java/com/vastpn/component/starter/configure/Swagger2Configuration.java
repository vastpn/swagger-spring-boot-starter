package com.vastpn.component.starter.configure;

import com.vastpn.component.starter.attribute.Swagger2Attribute;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
 * <pre>
 * <b>swagger2-spring-boot-starter 适配器.</b>
 * <b>Description:
 * 1、项目中引用Swagger、Swagger-ui组件
 *          <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger2</artifactId>
 *             <version>${springfox-swagger2.version}</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger-ui</artifactId>
 *             <version>${springfox-swagger-ui.version}</version>
 *         </dependency>
 * 2、配置Swagger属性
 * spring:
 *   boot:
 *     swagger2:
 *       enable: true   启用/禁用标志；生产环境建议关闭，设置该属性为false；其他环境开启API方便调试
 *       title: xxx
 *       description: xxx
 *       version: xxx
 *       contactName: xxx
 *       contactEmail: xxx
 *       contactUrl: xxx
 *       </b>
 *
 * <b>Author: 641597345@qq.com </b>
 * <b>Date: 2018/9/10 0010 13:58   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/9/10 0010 13:58          641597345@qq.com            new file.
 * <pre>
 */

@Configuration
@ConditionalOnProperty(prefix = "spring.boot.swagger2",name = "enable" ,havingValue = "true")
@EnableConfigurationProperties(Swagger2Attribute.class)
@EnableSwagger2
public class Swagger2Configuration {

    @Autowired
    private Swagger2Attribute swagger2Attribute;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swagger2Attribute.getEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swagger2Attribute.getTitle())
                .description(swagger2Attribute.getDescription())
                .termsOfServiceUrl(swagger2Attribute.getTermsOfServiceUrl())
                .contact(new Contact(swagger2Attribute.getContactName(), swagger2Attribute.getContactUrl(), swagger2Attribute.getContactEmail()))
                .version(swagger2Attribute.getVersion())
                .build();
    }

}
