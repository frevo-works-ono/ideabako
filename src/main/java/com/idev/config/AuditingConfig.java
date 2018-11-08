package com.idev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class AuditingConfig {
//	@Bean
//    public AuditorAware<String> createAuditorProvider() {
//        return new SecurityAuditor();
//    }

    @Bean
    public AuditingEntityListener createAuditingListener() {
        return new AuditingEntityListener();
    }
    
//    public static class SecurityAuditor implements AuditorAware<String> {
//        @Override
//        public String getCurrentAuditor() {
//            return "taka2";
//        }
//    }
}
