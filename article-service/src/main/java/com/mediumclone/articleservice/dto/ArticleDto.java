package com.mediumclone.articleservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Long author;
    private Date created_at;
    private Date updated_at;
    private String tags;
}
