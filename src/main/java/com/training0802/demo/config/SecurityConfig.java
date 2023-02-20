package com.training0802.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.builder()
//                .username("haidang")
//                .password(encoder().encode("123456"))
//                .roles("admin")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("mi")
//                .password(encoder().encode("123456"))
//                .roles("manager")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,user2);
//    }

    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("123456"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123456"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new AccountInfoToUserDetailsService();
    }

//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }

//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("manager")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        String usersByUsernameQuery =
//                "select username, password,role from tblAccount where username = ?";
//        String authsByUserQuery =
//                "select username, role from tblAccount where username = ?";
//
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
//        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
//        return userDetailsManager;
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager() {
//
//        return new CustomUserDetailsManager();
//    }
//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("bi")
//                .password(encoder().encode("123456"))
//                .roles("admin")
//                .build();
//        UserDetails admin = User.builder()
//                .username("bo")
//                .password(encoder().encode("123456"))
//                .roles("manager")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests((authr)->authr
                        .requestMatchers("/api/account/add").permitAll()
                        .anyRequest().permitAll()
//                        .requestMatchers("/api/account").hasAuthority("admin")
//                        .requestMatchers("/api/house/*").authenticated() // admin hay user thì định nghĩa trong controller
//                        .requestMatchers("/api/account/{id}").hasAuthority("user")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    };

}

