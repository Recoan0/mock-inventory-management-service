package com.ikea.irecover.inventorymanagement.domain.articleinventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ArticlePart {
    private String name;
    private Integer quantity;
}
