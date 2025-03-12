package com.ikea.irecover.inventorymanagement.domain.articleinventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Article {
    private String name;
    private List<ArticlePart> parts;
}
