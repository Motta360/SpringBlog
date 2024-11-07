package com.LucasMotta.SpringBlog.models;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String text;
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "user_posts")
	private BlogUser author;

	public Post() {
	}

	public Post(Long id, String text, LocalDate date, BlogUser author, String title) {
		this.id = id;
		this.text = text;
		this.date = date;
		this.author = author;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BlogUser getAuthor() {
		return author;
	}

	public void setAuthor(BlogUser author) {
		this.author = author;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, date, id, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(author, other.author) && Objects.equals(date, other.date) && Objects.equals(id, other.id)
				&& Objects.equals(text, other.text);
	}
	
	
	

}
