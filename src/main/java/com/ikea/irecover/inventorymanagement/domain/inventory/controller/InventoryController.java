package com.ikea.irecover.inventorymanagement.domain.inventory.controller;

import com.ikea.irecover.inventorymanagement.domain.inventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.UpdateInventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.inventory.service.InventoryService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public InventoryEntryEntity createInventoryEntry(
            @RequestBody InventoryEntryDto inventoryEntryDto) {
        return inventoryService.createInventoryEntry(inventoryEntryDto);
    }

    @PutMapping("/{inventoryEntryId}")
    public InventoryEntryEntity updateInventoryEntry(
            @PathVariable @NotNull UUID inventoryEntryId,
            @RequestBody UpdateInventoryEntryDto updateInventoryEntryDto) {
        return inventoryService.updateInventoryEntry(inventoryEntryId, updateInventoryEntryDto);
    }

    @DeleteMapping("/{inventoryEntryId}")
    public void deleteInventoryEntry(@PathVariable @NotNull UUID inventoryEntryId) {
        inventoryService.deleteInventoryEntry(inventoryEntryId);
    }

    @GetMapping("/{inventoryEntryId}")
    public InventoryEntryDto getInventoryEntryById(@PathVariable @NotNull UUID inventoryEntryId) {
        return inventoryService.getInventoryEntryById(inventoryEntryId);
    }

    @GetMapping("/all")
    public List<InventoryEntryDto> getAllInventoryEntries() {
        return inventoryService.getAllInventoryEntries();
    }

}
