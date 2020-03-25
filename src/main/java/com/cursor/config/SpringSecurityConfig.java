package com.cursor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Setter
@Getter
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationConfig authenticationConfig;

    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/", "/home")
                .permitAll()
                .antMatchers("/admin", "/admin/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/user/**")
                .hasAnyRole("USER")
                .anyRequest()
                .authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .permitAll()

                .and()

                .logout()
                .permitAll()

                .and()

                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationConfig).getDefaultUserDetailsService();
    }
}
