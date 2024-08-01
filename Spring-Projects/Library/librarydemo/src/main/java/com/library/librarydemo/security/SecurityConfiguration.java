package com.library.librarydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/home/**").hasRole("USER")
                                .requestMatchers("/books/**").hasRole("ADMIN")
                                .requestMatchers("/shelves/**").hasRole("ADMIN")
                                .requestMatchers("/authors/**").hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("src/main/resources/static/**")).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/home/list",true)
                                .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling( configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                );

        http.logout(
          logout -> logout
                  .logoutUrl("/logout")
                  .logoutSuccessUrl("/showMyLoginPage")
                  .invalidateHttpSession(true)
                  .deleteCookies("JSESSIONID")
                  .permitAll()
        );

        return http.build();
    }
}
