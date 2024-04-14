package com.mediumclone.articleservice.dao;

import com.mediumclone.articleservice.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagDao extends JpaRepository<Tag, Long> {
    boolean existsByTagName(String tagName);
    Tag findFirstByTagName(String tagName);
    List<Tag> findAllByTagName(String tagName);
}
