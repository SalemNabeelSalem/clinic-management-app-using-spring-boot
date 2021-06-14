package com.bit.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerDocConfig {

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();

            Arrays.asList(
                "clinic-application-apis"
            ).forEach(resourceName -> resources.add(loadResource(resourceName)));

            return resources;
        };
    }

    private SwaggerResource loadResource(String resource) {

        SwaggerResource swaggerResource = new SwaggerResource();

        swaggerResource.setName(resource);

        swaggerResource.setSwaggerVersion("2.0");

        swaggerResource.setLocation("/swagger-apis/" + resource + ".yaml");

        return swaggerResource;
    }
}