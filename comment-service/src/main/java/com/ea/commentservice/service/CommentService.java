package com.ea.commentservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ea.commentservice.dto.CommentDto;
import com.ea.commentservice.model.Comment;
import com.ea.commentservice.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	
	private final CommentRepository commentRepository;

	public void save(CommentDto commentDto) {
		commentRepository.save(mapDtoToEntity(commentDto));
	}
	
	private Comment mapDtoToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setContent(commentDto.getContent());
	//	comment.setUser(new User(commentDto.getUserId()));
	//	comment.setArticle( new Article(commentDto.getArticleId()));
		comment.setArticleId(commentDto.getArticleId());
		comment.setUserId(commentDto.getUserId());
		comment.setCreatedAt(LocalDateTime.now());
		comment.setUpdatedAt(LocalDateTime.now());
		return comment;
	}
	
	public List<CommentDto> getAllComments() {
		return commentRepository.findAll().stream().map(p ->{
			return mapEntityToDto(p);
		}).collect(Collectors.toList());
	}
	
	private CommentDto mapEntityToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setContent(comment.getContent());
		commentDto.setCommentId(comment.getCommentId());
		//commentDto.setUserId(comment.getUser().getUserId());
		//commentDto.setArticleId(comment.getArticle().getArticleId());
		commentDto.setArticleId(comment.getArticleId());
		commentDto.setUserId(comment.getUserId());
		commentDto.setCreatedAt(LocalDateTime.now());
		commentDto.setUpdatedAt(LocalDateTime.now());
		return commentDto;
	}

	public void updateComment(Long commentId, CommentDto commentDto) {
		commentRepository.save(updateData(commentId,commentDto));
	}

	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	private Comment updateData(Long commentId, CommentDto commentDto) {
		Comment oComment = commentRepository.findById(commentId).get();
		if(oComment != null) {
			oComment.setContent(commentDto.getContent());
		//	oComment.setArticle(new Article(commentDto.getArticleId()));
		//	oComment.setUser(new User(commentDto.getUserId()));
			oComment.setArticleId(commentDto.getArticleId());
			oComment.setUserId(commentDto.getUserId());
			oComment.setUpdatedAt(LocalDateTime.now());			
		}
		return oComment;
	}

	public List<CommentDto> getCommentsByArticle(Long articleId) {
		return commentRepository.findCommentsByArticle(articleId)
		.stream().map(p ->{
			return mapEntityToDto(p);
		}).collect(Collectors.toList());
	}
}
