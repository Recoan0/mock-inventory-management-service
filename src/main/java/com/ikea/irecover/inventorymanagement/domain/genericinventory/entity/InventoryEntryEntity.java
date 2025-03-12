package com.ikea.irecover.inventorymanagement.domain.genericinventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class InventoryEntryEntity {
    @Id
    private UUID inventoryEntryId;
    private String businessUnitCode;
    private String articleNo;
    private Integer quantity;
    private String originCode;
    private Boolean isSellable;
}
