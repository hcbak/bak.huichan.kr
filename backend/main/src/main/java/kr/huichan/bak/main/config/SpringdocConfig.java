package kr.huichan.bak.main.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SpringdocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .info(new Info()
            .title("Bak API Document")
            .version("v1")
            .description("bak.huichan.kr 백엔드 API 문서")
        ).externalDocs(new ExternalDocumentation()
            .description("Github")
            .url("https://github.com/hcbak/bak.huichan.kr/tree/master/backend/main")
        ).addTagsItem(new Tag()
            .name("Auth")
            .description("인증")
        );
    }

    @Bean
    OpenApiCustomizer springSecurityLoginEndpointCustomizer() {
        return openAPI -> {
            Operation operation = new Operation();

            Schema<?> schema = new ObjectSchema()
                    .addProperty("username", new StringSchema())
                    .addProperty("password", new StringSchema());
            
            // Request body type
            operation.requestBody(new RequestBody()
                .content(new Content()
                    .addMediaType(
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
                        new MediaType().schema(schema)))
            );

            // Responses
            operation.responses(new ApiResponses()
                .addApiResponse(
                    String.valueOf(HttpStatus.OK.value()), 
                    new ApiResponse().description(HttpStatus.OK.getReasonPhrase())
                        .addHeaderObject(
                            "Set-Cookie", 
                            new io.swagger.v3.oas.models.headers.Header().description(
                                "인증을 위한 Session ID"
                            ).schema(new StringSchema().example(
                                "JSESSIONID=<generated-session-id>; Path=/; HttpOnly")))
                        .addHeaderObject(
                            "x-session-id", 
                            new io.swagger.v3.oas.models.headers.Header().description(
                                "예비로 제공하는 JSESSIONID"
                            ).schema(new StringSchema().example(
                                "<generated-session-id>")))
                    )
                .addApiResponse(
                    String.valueOf(HttpStatus.UNAUTHORIZED.value()), 
                    new ApiResponse().description(HttpStatus.UNAUTHORIZED.getReasonPhrase()))
            );

            operation.addTagsItem("Auth");
            operation.setSummary("로그인");
            PathItem pathItem = new PathItem().post(operation);
            openAPI.getPaths().addPathItem("/api/v1/auth/sign-in", pathItem);
        };
    }

    @Bean
    OpenApiCustomizer springSecurityLogoutEndpointCustomizer() {
        return openAPI -> {
            Operation operation = new Operation();
            
            // Responses
            operation.responses(new ApiResponses()
                .addApiResponse(
                    String.valueOf(HttpStatus.OK.value()), 
                    new ApiResponse().description(HttpStatus.OK.getReasonPhrase())
                    .addHeaderObject(
                        "Set-Cookie",
                        new io.swagger.v3.oas.models.headers.Header().description(
                            "세션 삭제를 위한 JSESSIONID 만료 처리"
                        ).schema(new StringSchema().example("JSESSIONID=; Path=/; Max-Age=0; HttpOnly"))
                    )
                )
                .addApiResponse(
                    String.valueOf(HttpStatus.UNAUTHORIZED.value()), 
                    new ApiResponse().description(HttpStatus.UNAUTHORIZED.getReasonPhrase()))
            );

            operation.addTagsItem("Auth");
            operation.setSummary("로그아웃");
            PathItem pathItem = new PathItem().post(operation);
            openAPI.getPaths().addPathItem("/api/v1/auth/sign-out", pathItem);
        };
    }
}