package by.example.bookstore_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("by.example.bookstore_api"))
        .paths(regex("/.*"))
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Name")
        .description("Api Endpoint Decoration")
        .contact(new Contact("Ye", "yhtrh", "yahor.ankudovich@innowise.com"))
        .license("Apache 2.0")
        .licenseUrl("fgdfsfd")
        .version("1")
        .build();
  }
}