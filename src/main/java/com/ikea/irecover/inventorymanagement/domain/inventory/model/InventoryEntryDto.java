package com.ikea.irecover.inventorymanagement.domain.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryEntryDto {
    private UUID inventoryEntryId;
    private String businessUnitCode;
    private String articleNo;
    private Integer quantity;
    private String originCode;
    private Boolean isSellable;
}
