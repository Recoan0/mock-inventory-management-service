package com.ikea.irecover.inventorymanagement.domain.genericinventory.service;

import com.ikea.irecover.inventorymanagement.domain.genericinventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.exception.InventoryEntityNotFoundException;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.mapper.InventoryEntryEntityMapper;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.UpdateInventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.repository.InventoryEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryEntryRepository inventoryEntryRepository;
    private final InventoryEntryEntityMapper inventoryEntryEntityMapper;

    @Override
    public InventoryEntryDto getInventoryEntryById(UUID inventoryEntryId) {
        return inventoryEntryRepository.findById(inventoryEntryId)
                .map(inventoryEntryEntityMapper::toDto)
                .orElseThrow(() -> new InventoryEntityNotFoundException(inventoryEntryId));
    }

    @Override
    public List<InventoryEntryDto> getAllInventoryEntries() {
        return inventoryEntryRepository.findAll()
                .stream()
                .map(inventoryEntryEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryEntryEntity createInventoryEntry(InventoryEntryDto inventoryEntryDto) {
        return inventoryEntryRepository.save(inventoryEntryEntityMapper.toEntity(inventoryEntryDto));
    }

    @Override
    public InventoryEntryEntity updateInventoryEntry(UUID inventoryEntryId, UpdateInventoryEntryDto updateInventoryEntryDto) {
        return inventoryEntryRepository.findById(inventoryEntryId)
                .map(entity -> updateInventoryEntryEntity(entity, updateInventoryEntryDto))
                .map(inventoryEntryRepository::save)
                .orElseThrow(() -> new InventoryEntityNotFoundException(inventoryEntryId));
    }

    @Override
    public void deleteInventoryEntry(UUID inventoryEntryId) {
        inventoryEntryRepository.findById(inventoryEntryId)
                .orElseThrow(() -> new InventoryEntityNotFoundException(inventoryEntryId));
        inventoryEntryRepository.deleteById(inventoryEntryId);
    }

    private InventoryEntryEntity updateInventoryEntryEntity(
                InventoryEntryEntity inventoryEntryEntity,
                UpdateInventoryEntryDto updateInventoryEntryDto) {
        return inventoryEntryEntity.toBuilder()
                .quantity(updateInventoryEntryDto.getQuantity())
                .originCode(updateInventoryEntryDto.getOriginCode())
                .isSellable(updateInventoryEntryDto.getIsSellable())
                .build();
    }
}
