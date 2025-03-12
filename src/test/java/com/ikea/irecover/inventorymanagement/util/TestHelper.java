package com.ikea.irecover.inventorymanagement.util;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import com.ikea.irecover.inventorymanagement.domain.articleinventory.model.Article;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.UpdateInventoryEntryDto;

import java.util.UUID;

public class TestHelper {
    public static InventoryEntryEntity createInventoryEntryEntity(final UUID inventoryEntryId) {
        return InventoryEntryEntity.builder()
                .inventoryEntryId(inventoryEntryId)
                .businessUnitCode("kek")
                .articleNo("007")
                .quantity(3)
                .isSellable(true)
                .originCode("STO")
                .build();
    }

    public static InventoryEntryDto createInventoryEntryDto(final UUID inventoryEntryId) {
        return InventoryEntryDto.builder()
                .inventoryEntryId(inventoryEntryId)
                .businessUnitCode("kek")
                .articleNo("007")
                .quantity(3)
                .isSellable(true)
                .originCode("STO")
                .build();
    }

    public static UpdateInventoryEntryDto createUpdateInventoryEntryDto(final Integer quantity,
                                                                        final String originCode,
                                                                        final Boolean isSellable) {
        return UpdateInventoryEntryDto.builder()
                .quantity(quantity)
                .isSellable(isSellable)
                .originCode(originCode)
                .build();
    }

    public static ArticlePartEntity createArticlePartEntity(String articlePartName, Integer quantity) {
        return ArticlePartEntity.builder().name(articlePartName) .quantityAvailable(quantity).build();
    }
}
