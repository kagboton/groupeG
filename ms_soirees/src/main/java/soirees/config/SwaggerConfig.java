package soirees.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import soirees.controller.SoireeController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = {SoireeController.class})
@Configuration
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "0.0.1";
    private static final String LICENSE_TEXT = "La licence de cette API est gratuite mais il est formellement interdit d'usurper les droits" +
            " d'auteur";
    private static final String title = "REST API Soirées";
    private static final String description = "RESTful API Soirées regroupe ci-contre la documentation du module Soirées vous permettant de gérer au mieux l'organisation d'une soirée.\n C'est clairement intéressant d'utiliser ce que les autres ont déjà fait.\nEn cas de soucis techniques, envoyez nous un mail à l'adresse suivante : kook-yong.salogba@etu.univ-orleans.fr";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(SWAGGER_API_VERSION)
                .license(LICENSE_TEXT)
                .build();
    }

    @Bean
    public Docket eventApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

}
