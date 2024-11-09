package com.LucasMotta.SpringBlog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.PostRepository;


@Controller
public class PostController {
	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/post/{id}")
	public String getPostPage(@PathVariable Long id,Model model) {
		Post post = postRepository.getById(id);
		model.addAttribute("post", post);
		
		return "post";
	}

}
