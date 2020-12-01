package com.bacurilab.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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

import javax.sql.DataSource;

@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers("/test/**", "/register/**", "/swagger-ui**", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select email as principal, password as credentails, true from users where email=?")
//                .authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_roles where user_email=?")
//                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
//    }

//    @Override
//    public void configure(HttpSecurity http) throws Exception {          http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http
//                .authorizeRequests()
//                .antMatchers("/signup**")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() .csrf().disable()
//        ;
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET, "/agents/login");
//    }

//    @Override
//    protected void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        clients.inMemory()
////                .withClient(clientId)
////                .secret(clientSecret)
////                .scopes("read","write")
////                .authorizedGrantTypes("authorization_code", "refresh_token", "password");
//        //.autoApprove(true);
//    }

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
