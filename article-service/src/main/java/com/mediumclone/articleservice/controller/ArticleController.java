package com.mediumclone.articleservice.controller;

import com.mediumclone.articleservice.domain.Article;
import com.mediumclone.articleservice.dto.ArticleDto;
import com.mediumclone.articleservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService as;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ArticleDto article){
        System.out.println("\narticle:: " + article);
        if(as.saveArticle(article)){
            return ResponseEntity.status(HttpStatus.OK).body("added successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while saving");
        }
    }
    @GetMapping("/list")
    public List<ArticleDto> getAll(){
        return as.getArticles();
    }
    @GetMapping("/{id}")
    public ArticleDto getOne(@PathVariable Long id){
        System.out.println("\n\nInside getOne - GetMapping :: "+ id);
        return as.getArticle(id);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ArticleDto article){
        ArticleDto dbArticle = as.getArticle(article.getId());
        if(dbArticle == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("article not found");
        }
        if(as.updateArticle(dbArticle, article)){
            System.out.println("\n\n2");
            return ResponseEntity.status(HttpStatus.OK).body("updated successfully");
        }else {
            System.out.println("\n\n3");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong while updating the article");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ArticleDto article = as.getArticle(id);
        if(article == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("article not found");
        }
        if(as.deleteArticle(article)){
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while deleting");
        }
    }
    @GetMapping("/articlesContaining/{text}")
    public List<ArticleDto> getArticlesContaining(@PathVariable String text){
        return as.searchByText(text);
    }

    @GetMapping("/searchByTag/{tag}")
    public List<ArticleDto> getArticlesByTag(@PathVariable String tag){
        System.out.println("Searching articles containing tag :"+ tag);
        return as.searchByTag(tag);
    }
}
