package com.kpfu.itis.khakov.multiremark.config;

import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Rustam Khakov
 */
public class SecurityUtils {

	public static User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
