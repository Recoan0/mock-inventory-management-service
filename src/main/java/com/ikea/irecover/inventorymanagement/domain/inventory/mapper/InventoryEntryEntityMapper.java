package com.ikea.irecover.inventorymanagement.domain.inventory.mapper;

import com.ikea.irecover.inventorymanagement.domain.inventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.InventoryEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryEntryEntityMapper {
    InventoryEntryEntity toEntity(InventoryEntryDto inventoryEntryDto);
    InventoryEntryDto toDto(InventoryEntryEntity inventoryEntryEntity);
}
