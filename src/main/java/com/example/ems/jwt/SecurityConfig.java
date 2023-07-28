package com.example.ems.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired JwtFilter jwt;
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Primary
	public UserDetailsService userDetailService()
	{
		return new CustomUserDetails();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity hs) throws Exception{
		hs.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/admins/gettoken").permitAll()
		.antMatchers("/projects/**","/employees/**","/employee-projects/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and().exceptionHandling()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		hs.authenticationProvider(daoAuthenticationProvider());
		hs.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
		
		
		return hs.build();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider d=new DaoAuthenticationProvider();
		d.setUserDetailsService(userDetailService());
		d.setPasswordEncoder(passwordEncoder());
		return d;
	}

}
