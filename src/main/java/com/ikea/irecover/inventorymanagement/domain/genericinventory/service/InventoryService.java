package com.ikea.irecover.inventorymanagement.domain.genericinventory.service;

import com.ikea.irecover.inventorymanagement.domain.genericinventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.UpdateInventoryEntryDto;

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
