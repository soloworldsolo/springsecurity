package com.soloworld.security.security;

import static com.soloworld.security.security.ApplicationRole.ADMIN;
import static com.soloworld.security.security.ApplicationRole.ADMIN_TRAINEE;
import static com.soloworld.security.security.ApplicationRole.USER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  PasswordEncoder passwordEncoder;

  public SecurityConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().
        authorizeRequests().
        antMatchers("/user/**").hasRole(USER.name()).
        anyRequest().
        authenticated().and().httpBasic();
  }

  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    UserDetails user = User.builder().username("solomon").
        password(passwordEncoder.encode("helloworld")).
        roles(USER.name()).authorities(USER.grantedAuthoritySet())
        .build();
    UserDetails admin = User.builder().username("soloworld").
        password(passwordEncoder.encode("helloworld")).
        authorities(ADMIN.grantedAuthoritySet())
        .build();
    UserDetails darwin = User.builder().username("darwin").
        password(passwordEncoder.encode("helloworld")).roles(ADMIN_TRAINEE.name())
        .authorities(ADMIN_TRAINEE.grantedAuthoritySet())
        .build();
    return new InMemoryUserDetailsManager(user,
        admin, darwin);
  }
}
