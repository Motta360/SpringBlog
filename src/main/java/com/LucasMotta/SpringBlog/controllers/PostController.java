package com.LucasMotta.SpringBlog.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.PostRepository;
import com.LucasMotta.SpringBlog.repositories.UserRepository;



@Controller
public class PostController {
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/post/{id}")
	public String getPostPage(@PathVariable Long id,Model model) {
		String currentUserName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		Post post = postRepository.findById(id).get();
		
		String modifyEnable = "";
		if(post.getAuthor().getName().equals(currentUserName)) {
			modifyEnable = "Modificar Post";
			model.addAttribute("modifyEnable", modifyEnable);
		}else {
			model.addAttribute("modifyEnable", modifyEnable);
		}
		model.addAttribute("post", post);
		return "post";
	}
	
	@GetMapping("/editPost/{id}")
	public String getEditPostPage(@PathVariable Long id,Model model) {
		Post post = postRepository.findById(id).get();
		Post editedPost = new Post();
		model.addAttribute("post",post);
		model.addAttribute("editedPost", editedPost);
		return "editPost";
	}
	
	@GetMapping("/newPost")
	public String getNewPostPage(Model model){
		Post post = new Post();
		model.addAttribute("post", post);
		return "newPost";
		
	}
	
	@PostMapping("/newPost")
	public String postMethodName(@ModelAttribute Post post) {
		String currentUserName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		BlogUser currentUser = userRepository.findByName(currentUserName).get();
		
		post.setAuthor(currentUser);
		post.setDate(LocalDate.now());
		postRepository.save(post);
		
		return "redirect:/";
	}
	
	@PostMapping("/editPost/{id}")
	public String postEditedPost(@PathVariable Long id, Model model,@ModelAttribute Post editedPost) {
		
		Post post = postRepository.findById(id).get();
		post.setTitle(editedPost.getTitle());
		post.setText(editedPost.getText());
		
		postRepository.save(post);
		
		return "redirect:/post/"+ String.valueOf( post.getId());
		
		
	}
	

}
