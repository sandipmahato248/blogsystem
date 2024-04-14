package com.mediumclone.articleservice.service;

import com.mediumclone.articleservice.domain.Article;
import com.mediumclone.articleservice.dto.ArticleDto;

import java.util.List;

public interface IArticleService {
    public List<ArticleDto> getArticles();
    public ArticleDto getArticle(Long id);
    public boolean saveArticle(ArticleDto article) ;
    public boolean updateArticle(ArticleDto dbArticle, ArticleDto article);
    public boolean deleteArticle(ArticleDto article);
}
