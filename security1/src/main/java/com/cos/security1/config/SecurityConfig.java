package com.cos.security1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //메모리에 뜨게 설정
@EnableWebSecurity //활성화 해주기 => 스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인(기본필터체인)에 등록이 됩니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();//비활성화
		http.authorizeRequests()
			///user/**이 주소로 들어오면 .authenticated()인증이 필요하다.
			.antMatchers("/user/**").authenticated()
			///manager/**이 주소로 들어오면 admin이나 manager라는 권한이 있어야만 들어갈 수 있다.
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			//위 3개의 주소가 아니면 권한이 허용이 된다.
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login");
	}
}
