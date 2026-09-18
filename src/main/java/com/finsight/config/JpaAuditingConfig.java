package com.finsight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Without this, @CreatedDate/@CreatedBy/@LastModifiedDate/@LastModifiedBy on
 * AuditableEntity are inert — the annotations sit there but nothing populates
 * them, which is exactly what caused "Column 'created_at' cannot be null".
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // Hardcoded for now — once Spring Security/JWT is in, replace this
        // with the authenticated username from the SecurityContext.
        return () -> Optional.of("system");
    }
}
