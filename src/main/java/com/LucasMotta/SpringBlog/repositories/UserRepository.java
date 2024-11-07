package com.LucasMotta.SpringBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LucasMotta.SpringBlog.models.BlogUser;

public interface UserRepository extends JpaRepository<BlogUser, Long>{

}
