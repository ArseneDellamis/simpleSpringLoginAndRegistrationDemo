package com.DellamisDemoForm.LoginAndRegistration.securityLayer;

import com.DellamisDemoForm.LoginAndRegistration.customUserLog.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//authentication(login)
@Configuration
@EnableWebSecurity
public class webSecurityConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/users").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login.usernameParameter("email")
                                .defaultSuccessUrl("/users")
                                .permitAll()
                        )
                .logout(logout-> logout.logoutSuccessUrl("/").permitAll()
                );
        return http.build();
    }
}

