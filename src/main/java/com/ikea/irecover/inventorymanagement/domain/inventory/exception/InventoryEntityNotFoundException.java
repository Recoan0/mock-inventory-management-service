package com.ikea.irecover.inventorymanagement.domain.inventory.exception;

import lombok.experimental.StandardException;

import java.util.UUID;

public class InventoryEntityNotFoundException extends RuntimeException {
    public InventoryEntityNotFoundException(UUID inventoryEntryEntityId) {
        super(String.format("Could not find inventoryEntryEntity with id %s", inventoryEntryEntityId));
    }
}
