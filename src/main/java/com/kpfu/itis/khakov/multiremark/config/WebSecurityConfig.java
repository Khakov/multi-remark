package com.kpfu.itis.khakov.multiremark.config;

import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Rustam Khakov
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.formLogin()
				.loginPage(ApplicationUrls.LOGIN)
				.permitAll()
				.passwordParameter("password")
				.usernameParameter("login")
				.loginProcessingUrl(ApplicationUrls.LOGIN + "/process")
				.defaultSuccessUrl(ApplicationUrls.WORK)
				.failureUrl(ApplicationUrls.LOGIN + "?error=true")
				.and().authorizeRequests()
				.anyRequest().permitAll();
	}

	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/fonts/**");
	}
}




