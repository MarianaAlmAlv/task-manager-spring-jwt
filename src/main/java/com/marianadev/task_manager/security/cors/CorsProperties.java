package com.marianadev.task_manager.security.cors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Setter;
import lombok.Getter;

@Component
@ConfigurationProperties(prefix = "cors")
@Setter
@Getter
public class CorsProperties {
    private String allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;
    
}

