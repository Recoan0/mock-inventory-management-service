package com.ikea.irecover.inventorymanagement.domain.articleinventory.entity;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.exception.NotEnoughPartsException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePartEntity {
    @Id private String name;
    private Integer quantityAvailable;

    public ArticlePartEntity addQuantity(Integer quantity) {
        if (quantityAvailable == null) {
            quantityAvailable = 0;
        }
        quantityAvailable += quantity;
        return this;
    }

    public ArticlePartEntity subQuantity(Integer quantity) {
        if (quantityAvailable == null) {
            quantityAvailable = 0;
        }
        quantityAvailable -= quantity;
        if (quantityAvailable < 0) {
            throw new NotEnoughPartsException();
        }
        return this;
    }

    public int calculateAmountAvailable(Integer quantity) {
        if (quantityAvailable == null) {
            quantityAvailable = 0;
        }
        if(quantity == 0) {
            throw new IllegalArgumentException("Quantity cannot be zero");
        }
        return quantityAvailable / quantity;
    }
}
