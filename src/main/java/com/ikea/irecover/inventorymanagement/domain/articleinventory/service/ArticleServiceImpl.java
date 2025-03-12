package com.ikea.irecover.inventorymanagement.domain.articleinventory.service;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.exception.ArticlePartNotFoundException;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.exception.NotEnoughPartsException;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.mapper.ArticleMapper;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.Article;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.ArticlePart;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.repository.ArticlePartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticlePartRepository articlePartRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticlePartEntity getArticlePart(String articlePartName) {
        return articlePartRepository.findById(articlePartName)
                .orElseThrow(ArticlePartNotFoundException::new);
    }

    @Override
    public ArticlePartEntity addArticlePart(ArticlePart articlePart) {
        return articlePartRepository.findById(articlePart.getName())
                .map(articlePartEntity -> articlePartEntity.addQuantity(articlePart.getQuantity()))
                .or(() -> Optional.of(articleMapper.toArticlePartEntity(articlePart)))
                .map(articlePartRepository::save).get();
    }

    @Override
    public List<ArticlePartEntity> addArticle(Article article) {
        return article.getParts().stream().map(this::addArticlePart).collect(Collectors.toList());
    }

    @Override
    public ArticlePartEntity removeArticlePart(ArticlePart articlePart) {
        return articlePartRepository.findById(articlePart.getName())
                .map(articlePartEntity -> articlePartEntity.subQuantity(articlePart.getQuantity()))
                .map(articlePartRepository::save)
                .orElseThrow(NotEnoughPartsException::new);
    }

    @Override
    public Article buyArticle(Article article) {
        article.getParts().forEach(this::removeArticlePart);
        return article;
    }

    @Override
    public int calculateAvailability(Article article) {
        return article.getParts().stream()
                .mapToInt(this::calculateArticlePartsAvailable).min()
                .orElseThrow(() -> new IllegalArgumentException("Article has no parts"));
    }

    private int calculateArticlePartsAvailable(ArticlePart articlePart) {
        return getArticlePart(articlePart.getName()).calculateAmountAvailable(articlePart.getQuantity());
    }
}
