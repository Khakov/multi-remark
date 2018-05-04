package com.kpfu.itis.khakov.multiremark.config;

import com.kpfu.itis.khakov.multiremark.entity.roles.Role;
import com.kpfu.itis.khakov.multiremark.entity.roles.Teacher;
import com.kpfu.itis.khakov.multiremark.entity.roles.User;
import com.kpfu.itis.khakov.multiremark.repository.role.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Rustam Khakov
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = repository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Email '" + email + "' not found");
		}
		return repository.findByEmail(email);
	}
}
