package com.LucasMotta.SpringBlog.configuration;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.models.Post;
import com.LucasMotta.SpringBlog.repositories.PostRepository;
import com.LucasMotta.SpringBlog.repositories.UserRepository;

@Configuration
public class DataSeed implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		BlogUser u1 = new BlogUser(null, "lucas", "123");
		userRepository.save(u1);
		
		Post p1 = new Post(null, "Primeiro Post de todos", LocalDate.now(), u1, "First");
		postRepository.save(p1);
		
	}
	

}
