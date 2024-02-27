package com.sip.ams.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
