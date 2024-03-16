//package com.training0802.demo.config;
//
//import com.training0802.demo.service.mysql.AccountInfoToUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new AccountInfoToUserDetailsService();
//    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .cors().and()
////                .authorizeHttpRequests((authr) -> authr
////                        .requestMatchers("/api/acc/add").permitAll()
////                        .requestMatchers("/api/acc/login").permitAll()
//////                        .requestMatchers("/api/account/add").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .httpBasic(Customizer.withDefaults());
////
////        return http.build();
////    }
//}
//
