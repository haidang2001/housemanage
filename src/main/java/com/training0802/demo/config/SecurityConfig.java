package com.training0802.demo.config;

import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder encoder(){return new BCryptPasswordEncoder();
    }
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(encoder());
        return (AuthenticationProvider) authenticationProvider;
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

    public UserDetailsService userDetailsService(){
        return new AccountInfoToUserDetailsService();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/api/account/add").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/house").authenticated()
                .and().build();
    };

}

