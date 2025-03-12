package com.ikea.irecover.inventorymanagement.domain.genericinventory.repository;

import com.ikea.irecover.inventorymanagement.domain.genericinventory.entity.InventoryEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryEntryRepository extends JpaRepository<InventoryEntryEntity, UUID> {

}
