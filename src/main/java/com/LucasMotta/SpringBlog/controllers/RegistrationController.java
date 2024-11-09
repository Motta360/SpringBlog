package com.LucasMotta.SpringBlog.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.repositories.UserRepository;

@Controller
public class RegistrationController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String getRegistrationPage(Model model) {
		BlogUser u1 = new BlogUser();
		model.addAttribute("user", u1);
		return "register";
	}

	@GetMapping("/registerAE")
	public String getRegistrationAEPage(Model model) {
		BlogUser u1 = new BlogUser();
		model.addAttribute("user", u1);
		return "registerAE";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute BlogUser user) {
		String name = user.getName();
		String comparatorName;
		Optional<BlogUser> comparator = userRepository.findByName(name);
		if(comparator.isPresent()) {
			comparatorName = comparator.get().getName();
		}else {
			comparatorName = "";
		}
		if (!comparatorName.equalsIgnoreCase(name)) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);

			return "redirect:/login";
		} else {
			return "redirect:/registerAE";
		}
	}

}
