package com.LucasMotta.SpringBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LucasMotta.SpringBlog.models.BlogUser;
import com.LucasMotta.SpringBlog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
