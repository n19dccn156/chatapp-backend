package com.project.chatapp.config;

import com.project.chatapp.config.auth.CustomAccessDeniedHandler;
import com.project.chatapp.config.auth.CustomAuthEntryPoint;
import com.project.chatapp.config.auth.JwtAuthFilter;
import com.project.chatapp.dto.Enum.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthEntryPoint();
    }

    @Bean
    public JwtAuthFilter jwtAuthenticationFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().requestMatchers("*", "/**", "/*", "/ws/**").permitAll();
//        http.authorizeRequests().requestMatchers("/admin/**").hasAuthority(ERole.ADMIN.name());
//        http.authorizeRequests().requestMatchers(
//                "/api/accounts/**",
//                "/api/friends/**",
//                "/api/groups/**").hasAnyAuthority(ERole.USER.name(), ERole.ADMIN.name());
//        http.authorizeRequests().requestMatchers(
//                "/swagger-ui/**",
//                "/api-docs/**",
//                "/api/pub/**").permitAll();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        // authentication exceptions
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
        // phai verify truoc de add roles for user details manage url
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().anyRequest();
//        // Tell Spring to ignore securing the handshake endpoint. This allows the handshake to take place unauthenticated
////        web.ignoring().antMatchers("/stomp/**");
//
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ws/**");
    }
}
