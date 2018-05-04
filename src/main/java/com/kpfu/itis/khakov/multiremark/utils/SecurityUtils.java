package com.kpfu.itis.khakov.multiremark.utils;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Rustam Khakov
 */
public class SecurityUtils {

	public static User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static Role getUserAuthtority(User user) {
		return user.getAuthorities().stream().findFirst().map(r->Role.valueOf(r.getAuthority())).orElse(Role.ROLE_STUDENT);
	}

}
