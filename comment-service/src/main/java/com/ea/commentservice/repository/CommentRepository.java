package com.ea.commentservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ea.commentservice.dto.CommentDto;
import com.ea.commentservice.model.Comment;

import antlr.collections.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("select c from Comment c where c.articleId=?1")
	java.util.List<Comment> findCommentsByArticle(Long articleId);


}
