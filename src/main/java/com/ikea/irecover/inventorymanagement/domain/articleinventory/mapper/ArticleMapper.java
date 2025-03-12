package com.ikea.irecover.inventorymanagement.domain.articleinventory.mapper;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.ArticlePart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {

    @Mapping(source = "quantityAvailable", target = "quantity")
    ArticlePart toArticlePart(ArticlePartEntity articlePartEntity);

    @Mapping(source = "quantity", target = "quantityAvailable")
    ArticlePartEntity toArticlePartEntity(ArticlePart articlePart);
}
