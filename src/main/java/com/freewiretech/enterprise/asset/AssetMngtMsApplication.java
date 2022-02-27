package com.freewiretech.enterprise.asset;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class AssetMngtMsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssetMngtMsApplication.class, args);
  }

  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Asset Management API")
                .description("Asset Management services to manage Boost Devices and Boost Groups")
                .version("1.0.0"));
  }

  @Bean
  public GroupedOpenApi boostGroupOpenApi() {
    String[] paths = {"/boostGroup/**"};
    return GroupedOpenApi.builder().group("boostGroup").pathsToMatch(paths).build();
  }
}
