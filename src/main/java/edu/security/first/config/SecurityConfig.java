package edu.security.first.config;

import edu.security.first.handler.CustomLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  static final private String[] permitPaths = {"/", "/index", "/hello", "/signinpage", "/signup", "/login", "/signin"};

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable() // NOTE : custom form을 사용할 때, disable되어 있지 않으면 아무런 동작을 안한다
        .authorizeRequests()
        .antMatchers(permitPaths).permitAll() // 매칭되는 경로는 인증 불필요
        .anyRequest().authenticated()  // 그외 경로에는 인증 필요
        .and()
        // 밑의 설정을 해주지 않는다면 access denied와 함께 403에러가 발생함 
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login") // 사용자가 로그인시 스프링에 요청할 로그인 로직 처리 주소, 컨트롤러에 없어도 문제 없음
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(new CustomLoginSuccessHandler()) // 로그인 성공시 사용 할 핸들러 등록
        .and()
        .logout()
        .logoutUrl("/signout")  // 사용자가 스프링에 로그아웃을 요청할 주소, 컨트롤러에 없어도 문제발생 안함 (= loginProcessingUrl)
        .logoutSuccessUrl("/login"); //로그아웃 성공시 리다이렉트 시킬 경로
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
