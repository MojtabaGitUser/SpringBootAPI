package Heidarpanah.Mojtaba.onlineshop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig {
@Bean
 fun api():Docket{

     return Docket(DocumentationType.SWAGGER_2)
         .select()
         .paths(PathSelectors.ant("/api/**"))
         .apis(RequestHandlerSelectors.basePackage("Heidarpanah.Mojtaba.onlineshop.controllers"))
         .build()
 } // Docket comes from springfox library.

    @Bean
    //this methode inserted in web config to remove index.html from  http://localhost:8080/swagger-ui
    fun defaultViewResolver(): InternalResourceViewResolver? {
        return InternalResourceViewResolver()
    }
}