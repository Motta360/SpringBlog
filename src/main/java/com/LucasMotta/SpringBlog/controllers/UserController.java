package com.LucasMotta.SpringBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user/{id}")
	public String getUserPage(@PathVariable long id, Model model) {
		BlogUser user = userRepository.findById(id).get();
		List<Post> posts = user.getPosts();
		model.addAttribute("posts", posts);
		model.addAttribute("user", user);
		return "userPage";
	}

}
