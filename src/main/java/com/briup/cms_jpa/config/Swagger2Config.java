package com.briup.cms_jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
/*
	整合swagger
	@Configuration 代表该类是配置类
	@EnableSwagger2 @SpringBootApplication不能自动扫描该类，如同mapper一样。所以需要手动配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	//所注释内容没有authorization入口
	/*@Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.briup.cms.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }
*/

	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//需要生成接口文档的包
				.apis(RequestHandlerSelectors.basePackage("com.briup.cms_jpa.web"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars)
				.apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标注信息
				.title("看点咨询接口文档")
				.description("包头师范4班团队开发")
				.termsOfServiceUrl("http://www.briup.com")
				.version("1.0")
				.build();
	}
}

