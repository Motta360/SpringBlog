package com.LucasMotta.SpringBlog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.PostRepository;

@Controller
public class HomeController {
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/")
	public String getHomePage(Model model) {
		String currentUser;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				currentUser = "Bem-vindo: "+((UserDetails) principal).getUsername() ;
			}else {
				currentUser = "Você não está logado no momento";
			}
		}else {
			currentUser = "Você não está logado no momento";
		}
		
		List<Post> posts = postRepository.findAll();
		List<Post> posts2 = new ArrayList<>();
		
		for (int i = posts.size()-1; i >= 0 ; i--) {
			posts2.add(posts.get(i));
			
		}
		
		model.addAttribute("posts",posts2);
		model.addAttribute("currentUser", currentUser);
		return "index";
	}

}
