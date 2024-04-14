package com.mediumclone.articleservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mediumclone.articleservice.dao.ArticleDao;
import com.mediumclone.articleservice.dao.TagDao;
import com.mediumclone.articleservice.domain.Article;
import com.mediumclone.articleservice.domain.Tag;
import com.mediumclone.articleservice.dto.ArticleDto;

@Service
@Transactional
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TagDao tagDao;

    public List<ArticleDto> getArticles() {
        Iterable<Article> articles = articleDao.findAll();
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articles) {
            ArticleDto articleDto = entityToDtoMapper(article);
            articleDtos.add(articleDto);
        }
//        return articleDao.findAll();
        return articleDtos;
    }

    public ArticleDto getArticle(Long id) {
        return this.entityToDtoMapper(articleDao.getById(id));
    }

    public ArticleDto entityToDtoMapper(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setAuthor(article.getAuthor());
        articleDto.setCreated_at(article.getCreated_at());
        articleDto.setUpdated_at(article.getUpdated_at());
        StringJoiner joiner = new StringJoiner(",");
        for (Tag tag: article.getTags()) {
            joiner.add(tag.getTagName());
        }
        articleDto.setTags(joiner.toString());
        return articleDto;
    }

    public boolean saveArticle(ArticleDto article) {
        try {
            Article savedArticle = articleDao.save(dtoToEntityMapper(article));
            if (savedArticle != null) return true;
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Article dtoToEntityMapper(ArticleDto dtoArticle){
        Article article = new Article();
        article.setId(dtoArticle.getId());
        article.setTitle(dtoArticle.getTitle());
        article.setContent(dtoArticle.getContent());
        article.setAuthor(dtoArticle.getAuthor());
        article.setCreated_at(new Date());
        article.setUpdated_at(new Date());
        article = this.addTags(article, dtoArticle.getTags());
        return article;
    }
    
    public Article addTags(Article article, String tags){
        if (tags == null){
            return article;
        }
        String tagsArr[] = tags.split(",");
        for (String tag: tagsArr) {
            tag = tag.trim();
            Tag tagObj;// = new Tag(tag);
            if (!tagDao.existsByTagName(tag)){
                tagObj = new Tag(tag);
                tagDao.save(tagObj);
            } else {
                tagObj = tagDao.findFirstByTagName(tag);
            }
            article.setTag(tagObj);
        }
        return article;
    }
    public List<ArticleDto> searchByTag(String tag){
        List<ArticleDto> articleDto = new ArrayList<>();
        Tag tagObj = tagDao.findFirstByTagName(tag);
        List<Article> articles = articleDao.findByTagsContaining(tagObj);
        for (Article article: articles) {
            articleDto.add(entityToDtoMapper(article));
        }
        return articleDto;
    }
    public boolean updateArticle(ArticleDto dbArticle, ArticleDto articleUpdateDto) {
        try {
            Article articleUpdate = this.dtoToEntityMapper(articleUpdateDto);
            Article article = articleDao.findById(articleUpdate.getId()).orElse(null);

            if (article != null) {
                article.setId((articleUpdateDto.getId() == null) ? dbArticle.getId() : articleUpdateDto.getId());
                article.setTitle((articleUpdate.getTitle() == null) ? dbArticle.getTitle() : articleUpdateDto.getTitle());
                article.setContent((articleUpdate.getContent() == null) ? dbArticle.getContent() : articleUpdateDto.getContent());
                article.setAuthor((articleUpdate.getAuthor() == null) ? dbArticle.getAuthor() : articleUpdateDto.getAuthor());
                article.setCreated_at(article.getCreated_at());
                article.setUpdated_at(new Date());
                if (articleUpdateDto.getTags() != null) {
                    article.resetTags();
                }
                article = this.addTags(article, articleUpdateDto.getTags());
                articleDao.save(article);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed to update item
        }
    }

    public boolean deleteArticle(ArticleDto article) {
        try {
            articleDao.delete(dtoToEntityMapper(article));
            return (!articleDao.existsById(article.getId()));
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<ArticleDto> searchByText(String text){
        List<ArticleDto> articleDto = new ArrayList<>();
        List<Article> articles = articleDao.findByContentOrTitleContaining(text, text);
        for (Article article: articles) {
            articleDto.add(entityToDtoMapper(article));
        }
        return articleDto;
    }
}
