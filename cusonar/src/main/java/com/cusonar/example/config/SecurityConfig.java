package com.cusonar.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.cusonar.example.user.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/user/login").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/user").hasAuthority("USER")
			.antMatchers("/admin").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
//		.formLogin()
//			.and()
		.logout();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
    	return new HeaderHttpSessionStrategy();
    }
    
//    @Bean
//    public JedisConnectionFactory connectionFactory() throws URISyntaxException {
//    	JedisConnectionFactory redis = new JedisConnectionFactory();
//    	redis.setHostName("localhost");
//    	redis.setPort(6379);
//    	return redis;
//    }

}
