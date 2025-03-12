package com.ikea.irecover.inventorymanagement.domain.articleinventory.service;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.Article;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.ArticlePart;

import java.util.List;

public interface ArticleService {
    ArticlePartEntity getArticlePart(String articlePartName);
    ArticlePartEntity addArticlePart(ArticlePart articlePart);
    List<ArticlePartEntity> addArticle(Article article);
    ArticlePartEntity removeArticlePart(ArticlePart articlePart);
    Article buyArticle(Article article);
    int calculateAvailability(Article article);
}
