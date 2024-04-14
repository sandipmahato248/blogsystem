package com.ea.commentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ea.commentservice.dto.CommentDto;
import com.ea.commentservice.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto){
		commentService.save(commentDto);
		return ResponseEntity.ok("Comment Sucessfully added");
	}
	
	@GetMapping
	public ResponseEntity<?> getAllComments(){
		return ResponseEntity.ok().body(commentService.getAllComments());
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, 
			@RequestBody CommentDto commentDto){
		commentService.updateComment(commentId,commentDto);
		return ResponseEntity.ok("Update Sucessful");
		
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable("commentId") Long commentId){
		commentService.deleteComment(commentId);
		return ResponseEntity.ok("Delete sucessful");
	}
	
	@GetMapping("/{articleId}")
	public ResponseEntity<?> getCommentsByArticle(@PathVariable("articleId") Long articleId){
		return ResponseEntity.ok().body(commentService.getCommentsByArticle(articleId));
	}

}
