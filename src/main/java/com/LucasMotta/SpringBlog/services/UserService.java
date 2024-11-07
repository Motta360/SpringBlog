package com.LucasMotta.SpringBlog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<BlogUser> user = userRepository.findByName(username);
		
		if(user.isPresent()) {
			BlogUser userObj = user.get();
			
			return User.builder().username(userObj.getName()).password(userObj.getPassword()).build();
		}else {
			throw new UsernameNotFoundException(username);
		}

	}
	

}
