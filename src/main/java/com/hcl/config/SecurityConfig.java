package com.hcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("Me")
		.password("notmyrealpassword")
		.roles("USER")
		.and()
		.withUser("Simon")
		.password("chosen1")
		.roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder getPwdEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/addProduct").hasRole("ADMIN")
		.antMatchers("/userAllProducts").hasAnyRole("USER","ADMIN")
		.antMatchers("/adminAllProducts").hasRole("ADMIN")
		.antMatchers("/update").hasRole("ADMIN")
		.antMatchers("/userUpdate").hasRole("USER")
		.antMatchers("/userDelete").hasRole("USER")
		.antMatchers("/delete").hasRole("ADMIN")
		.and().formLogin()
		.and()
		.csrf().disable(); // I don't think we talked about tokens and I don't know
		// how to inject them. I'm sure this is not good practice security wise but it
		//works for now
	}
	
}
