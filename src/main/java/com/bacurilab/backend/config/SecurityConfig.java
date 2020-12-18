package com.bacurilab.backend.config;

import com.bacurilab.backend.filters.WebSecurityCorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
//                .regexMatchers("/.*")
                .antMatchers("/test/**", "/register/**", "/role/**", "/swagger-ui**", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO Auto-generated method stub
        return new BCryptPasswordEncoder();
    }

}


//package com.bacurilab.backend.config;
//public class SecurityConfig{
//
//}
