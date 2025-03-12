package com.ikea.irecover.inventorymanagement.domain.articleinventory.service;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.exception.ArticlePartNotFoundException;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.mapper.ArticleMapper;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.Article;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.ArticlePart;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.Table;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.repository.ArticlePartRepository;
import com.ikea.irecover.inventorymanagement.util.TestHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class ArticleServiceImplTest {
    @MockitoBean private ArticlePartRepository articlePartRepository;
    private final  ArticleMapper articleMapper;
    private final ArticleServiceImpl articleServiceImpl;

    @Test
    public void getArticlePart_works() {
        String articlePartName = "tableTop";
        Integer quantity = 5;
        ArticlePartEntity articlePartEntity = TestHelper.createArticlePartEntity(articlePartName, quantity);
        when(articlePartRepository.findById(articlePartName)).thenReturn(Optional.of(articlePartEntity));

        ArticlePartEntity partResult = articleServiceImpl.getArticlePart(articlePartName);

        assertEquals(articlePartName, partResult.getName());
        assertEquals(quantity, articlePartEntity.getQuantityAvailable());
    }

    @Test
    public void getArticlePart_notFoundThrows() {
        when(articlePartRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ArticlePartNotFoundException.class, () -> articleServiceImpl.getArticlePart("x"));
    }

    @Test
    public void addArticlePart_worksWhenArticlePartPresent() {
        String name = "tableTop";
        int originalQuantity = 1;
        int addQuantity = 2;
        ArticlePartEntity tableTopEntity = TestHelper.createArticlePartEntity(name, originalQuantity);
        ArticlePart articlePart = new ArticlePart(name, addQuantity);
        when(articlePartRepository.findById(name)).thenReturn(Optional.of(tableTopEntity));
        when(articlePartRepository.save(any(ArticlePartEntity.class))).thenAnswer(returnsFirstArg());
        ArticlePartEntity partResult = articleServiceImpl.addArticlePart(articlePart);

        verify(articlePartRepository.save(any(ArticlePartEntity.class)));
        assertEquals(name, partResult.getName());
        assertEquals(originalQuantity + addQuantity, partResult.getQuantityAvailable());
    }

    @Test
    public void addArticlePart_worksWhenArticlePartMissing() {
        String name = "tableTop";
        int addQuantity = 2;
        ArticlePart articlePart = new ArticlePart(name, addQuantity);
        when(articlePartRepository.findById(name)).thenReturn(Optional.empty());
        when(articlePartRepository.save(any(ArticlePartEntity.class))).thenAnswer(returnsFirstArg());
        ArticlePartEntity partResult = articleServiceImpl.addArticlePart(articlePart);

        verify(articlePartRepository.save(any(ArticlePartEntity.class)));
        assertEquals(name, partResult.getName());
        assertEquals(addQuantity, partResult.getQuantityAvailable());
    }

    @Test
    public void addArticle_works() {
        Article article = new Table();

        when(articlePartRepository.findById(any()))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.empty());
        when(articlePartRepository.save(any(ArticlePartEntity.class)))
                .thenAnswer(returnsFirstArg())
                .thenAnswer(returnsFirstArg());

        List<ArticlePartEntity> articlePartEntities = articleServiceImpl.addArticle(article);

        IntStream.range(0, articlePartEntities.size()).forEach(i -> {
            assertEquals(article.getParts().get(i).getName(), articlePartEntities.get(i).getName());
            assertEquals(article.getParts().get(i).getQuantity(), articlePartEntities.get(i).getQuantityAvailable());
        });
    }
}
