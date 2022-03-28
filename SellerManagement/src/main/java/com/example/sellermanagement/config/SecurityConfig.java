package com.example.sellermanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String ADMIN_ROLE = "ADMIN";
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                .antMatchers("login").permitAll()
                .antMatchers("/addadmin").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/getcurrentuser").hasAnyRole("USER", ADMIN_ROLE)
                .antMatchers("/addproduct").hasAnyRole("USER")
                .antMatchers("/getproducts").hasAnyRole("USER")
                .antMatchers("/editproduct").hasAnyRole("USER")
                .antMatchers("/deleteproduct").hasAnyRole("USER", ADMIN_ROLE)
                .antMatchers("/deleteaccount").hasAnyRole("USER")
                .antMatchers("/getallproducts").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/setuserstatus").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/getallusers").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/totalitemsno").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/totalsellersno").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/pendingsellers").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/approvedsellers").hasAnyRole(ADMIN_ROLE)
                .antMatchers("/deniedsellers").hasAnyRole(ADMIN_ROLE)
                .anyRequest().fullyAuthenticated().and().httpBasic()
                .and().logout().logoutUrl("/logout").permitAll();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
