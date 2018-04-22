package com.kpfu.itis.khakov.multiremark.controller;

/**
 * @author Rustam Khakov
 */

import com.kpfu.itis.khakov.multiremark.utils.ApplicationUrls;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping(value = ApplicationUrls.LOGIN)
	public String getLoginPage(@RequestParam(name = "error", required = false) Boolean error,
							   Model model) {
		if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return "redirect:" + ApplicationUrls.WORK;
		}

		if (error != null && error) {
			model.addAttribute("error", true);
		}
		return "login";
	}
}
