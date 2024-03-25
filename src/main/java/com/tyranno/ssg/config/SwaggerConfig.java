package com.tyranno.ssg.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Tyranno_developes API")
                .description("Springdoc을 사용한 Swagger UI 테스트")
                .version("1.0.0");
    }
//    @Configuration public class SwaggerConfiguration {
//        private static final String REFERENCE = "Authorization 헤더 값";
//
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                    .paths(PathSelectors.any())
//                    .build()
//                    .securityContexts(List.of(securityContext()))
//                    .securitySchemes(List.of(securityScheme()));
//        }
//
//        private SecurityContext securityContext() {
//            return SecurityContext.builder()
//                    .securityReferences(securityReferences())
//                    .operationSelector(operationContext -> true)
//                    .build();
//        }
//
//        private List securityReferences() {
//            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//            authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
//            return List.of(new SecurityReference(REFERENCE, authorizationScopes));
//        }
//
//        private ApiKey securityScheme() { // 어떠한 헤더에 값을 대입할 것인가: Authorization 헤더 String targetHeader = "Authorization"; return new ApiKey(REFERENCE, targetHeader, "header"); } }
//        }
//   }

}