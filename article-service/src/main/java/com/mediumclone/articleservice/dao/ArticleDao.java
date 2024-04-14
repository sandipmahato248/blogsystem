package com.mediumclone.articleservice.dao;

import com.mediumclone.articleservice.domain.Article;
import com.mediumclone.articleservice.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long> {
//    List<Article> findByContentContainingOrTitleContaining(String text1, String text2);
    List<Article> findByContentOrTitleContaining(String contentStr, String titleStr);
    List<Article> findByTagsContaining(Tag tag);
}
