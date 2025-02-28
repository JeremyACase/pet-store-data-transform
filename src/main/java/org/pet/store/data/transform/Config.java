package org.pet.store.data.transform;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * A top-level configuration class for AMIGOS.
 */
@Configuration
@EnableWebMvc
public class Config {

    /**
     * Ensure time is always in UTC.
     */
    @PostConstruct
    public void init() {
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
