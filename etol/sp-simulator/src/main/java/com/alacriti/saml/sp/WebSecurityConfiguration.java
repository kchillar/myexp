package com.alacriti.saml.sp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Configuration
public class WebSecurityConfiguration {
    
    Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        logger.info("building WebSecurityConfiguration using AuthFilter");
        http.addFilterAfter(new AuthFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
     
}
