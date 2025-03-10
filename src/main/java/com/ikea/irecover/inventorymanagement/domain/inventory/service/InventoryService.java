package com.ikea.irecover.inventorymanagement.domain.inventory.service;

import com.ikea.irecover.inventorymanagement.domain.inventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.UpdateInventoryEntryDto;

import java.util.List;
import java.util.UUID;

public interface InventoryService {
    InventoryEntryDto getInventoryEntryById(UUID id);
    List<InventoryEntryDto> getAllInventoryEntries();
    InventoryEntryEntity createInventoryEntry(InventoryEntryDto inventoryEntryDto);
    InventoryEntryEntity updateInventoryEntry(UUID inventoryEntryId,
                                              UpdateInventoryEntryDto updateInventoryEntryDto);
    void deleteInventoryEntry(UUID inventoryEntryId);
}
