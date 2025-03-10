package com.ikea.irecover.inventorymanagement.domain.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryEntryDto {
    private Integer quantity;
    private String originCode;
    private Boolean isSellable;
}
