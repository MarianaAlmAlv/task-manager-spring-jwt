package com.marianadev.task_manager.security.jwt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class JwtProperties {

    private String secret;
    private long expiration;

}