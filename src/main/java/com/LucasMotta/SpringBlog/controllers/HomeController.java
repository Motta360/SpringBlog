package com.LucasMotta.SpringBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.PostRepository;

@Controller
public class HomeController {
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/")
	public String getHomePage(Model model) {
		List<Post> posts = postRepository.findAll();
		model.addAttribute("posts",posts);
		return "index";
	}

}
