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
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

	@Autowired
	private MyCorsFilter myCorsFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.formLogin()
				.loginPage(ApplicationUrls.LOGIN)
				.permitAll()
				.passwordParameter("password")
				.usernameParameter("login")
				.loginProcessingUrl("/api/login").successHandler(
				new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						//do nothing
					}
				})
				.failureUrl(ApplicationUrls.LOGIN + "?error=true")
				.and().logout().logoutUrl(ApplicationUrls.LOGOUT).deleteCookies("JSESSIONID").logoutSuccessHandler(
				new LogoutSuccessHandler() {
					@Override
					public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
						for (Cookie cookie : httpServletRequest.getCookies()) {
							if (cookie.getName().equals("JSESSIONID")) {
								cookie.setMaxAge(0);
							}
						}
					}
				})
				.and().authorizeRequests()
				.anyRequest().permitAll()
				.and()
				.addFilterBefore(myCorsFilter, ChannelProcessingFilter.class);
	}

	@Override
	public void configure(WebSecurity security) {
		security.ignoring().antMatchers("/fonts/**");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}




