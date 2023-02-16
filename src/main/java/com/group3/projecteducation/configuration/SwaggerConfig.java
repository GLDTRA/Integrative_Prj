package com.group3.projecteducation.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springBlogPessoalOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Education")
                        .description("Projeto desenvolvido para unir professores à alunos em busca de democratizar o acesso" +
                                " à educação técnica e profissional.")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Grupo 3")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/GLDTRA/Integrative_Prj"));
    }
    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
                apiResponses.addApiResponse("201", createApiResponse("Objeto persistido!"));
                apiResponses.addApiResponse("204", createApiResponse("Objeto excluído!"));
                apiResponses.addApiResponse("400", createApiResponse("Erro na requisição!"));
                apiResponses.addApiResponse("401", createApiResponse("Acesso não autorizado!"));
                apiResponses.addApiResponse("404", createApiResponse("Objeto não encontrado!"));
                apiResponses.addApiResponse("500", createApiResponse("Erro na aplicação!"));
            }));
        };
    }

    private ApiResponse createApiResponse(String message){
        return new ApiResponse().description(message);
    }
}

