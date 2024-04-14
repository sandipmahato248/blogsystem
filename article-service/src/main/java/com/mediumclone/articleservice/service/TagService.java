package com.mediumclone.articleservice.service;

import com.mediumclone.articleservice.dao.TagDao;
import com.mediumclone.articleservice.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    public List<Tag> getAllTags(){
        return tagDao.findAll();
    }

    public Tag getTagById(Long id){
        return tagDao.getById(id);
    }
}
