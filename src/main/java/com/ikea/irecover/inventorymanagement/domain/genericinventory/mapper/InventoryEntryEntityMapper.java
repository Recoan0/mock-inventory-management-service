package com.ikea.irecover.inventorymanagement.domain.genericinventory.mapper;

import com.ikea.irecover.inventorymanagement.domain.genericinventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.genericinventory.model.InventoryEntryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryEntryEntityMapper {
    InventoryEntryEntity toEntity(InventoryEntryDto inventoryEntryDto);
    InventoryEntryDto toDto(InventoryEntryEntity inventoryEntryEntity);
}
