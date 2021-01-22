package edu.security.first.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/index", "/hello").permitAll() // 매칭되는 경로는 인증 불필요
        .anyRequest().authenticated()  // 그외 경로에는 인증 필요
        .and()
        // 밑의 설정을 해주지 않는다면 access denied와 함께 403에러가 발생함 
        .formLogin()
        .and()
        .httpBasic();
  }
}
