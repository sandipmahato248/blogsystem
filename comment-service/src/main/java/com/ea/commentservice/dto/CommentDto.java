package com.ea.commentservice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {

	private Long commentId;

	private String content;
	
	private Long articleId;
	
	private Long userId;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
