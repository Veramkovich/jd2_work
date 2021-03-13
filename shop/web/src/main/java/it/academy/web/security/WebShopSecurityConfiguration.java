package it.academy.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebShopSecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //.passwordEncoder(passwordEncoder())
                .withUser("user")
                .password("{noop}password")
                //.password("{bcrypt}$2a$10$I2l8zsdXTFfw5eHMKJ0SEOlfvZmhYdpDt23U7UAexP0o/lXLBy2P2")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}password")
                //.password("{bcrypt}$2a$10$I2l8zsdXTFfw5eHMKJ0SEOlfvZmhYdpDt23U7UAexP0o/lXLBy2P2")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/product").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .formLogin();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


}
