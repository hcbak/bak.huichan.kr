package kr.huichan.bak.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${CORS_ORIGIN}")
    private String CORS_ORIGIN;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_ORIGIN)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
