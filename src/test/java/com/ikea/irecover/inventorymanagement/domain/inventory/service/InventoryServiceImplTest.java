package com.ikea.irecover.inventorymanagement.domain.inventory.service;


import com.ikea.irecover.InventoryManagementSystemApplication;
import com.ikea.irecover.inventorymanagement.domain.inventory.entity.InventoryEntryEntity;
import com.ikea.irecover.inventorymanagement.domain.inventory.exception.InventoryEntityNotFoundException;
import com.ikea.irecover.inventorymanagement.domain.inventory.mapper.InventoryEntryEntityMapper;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.InventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.inventory.model.UpdateInventoryEntryDto;
import com.ikea.irecover.inventorymanagement.domain.inventory.repository.InventoryEntryRepository;
import com.ikea.irecover.inventorymanagement.util.TestHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = InventoryManagementSystemApplication.class)
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class InventoryServiceImplTest {
    @MockitoBean private InventoryEntryRepository inventoryEntryRepository;
    private final InventoryEntryEntityMapper inventoryEntryEntityMapper;
    private final InventoryServiceImpl inventoryService;

    @Test
    void testGetInventoryEntryById_works() {
        final UUID uuid = UUID.randomUUID();
        final InventoryEntryEntity inventoryEntryEntity = TestHelper.createInventoryEntryEntity(uuid);

        when(inventoryEntryRepository.findById(uuid)).thenReturn(Optional.of(inventoryEntryEntity));

        final InventoryEntryDto inventoryEntryDto = inventoryService.getInventoryEntryById(uuid);
        assertEquals(inventoryEntryEntityMapper.toDto(inventoryEntryEntity), inventoryEntryDto);
    }

    @Test
    void testGetInventoryEntryById_notFoundThrowsError() {
        when(inventoryEntryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InventoryEntityNotFoundException.class,
                () -> inventoryService.getInventoryEntryById(UUID.randomUUID()));
    }

    @Test
    void testGetAllInventoryEntries_worksWithMultipleEntries() {
        final InventoryEntryEntity inventoryEntryEntity = TestHelper.createInventoryEntryEntity(UUID.randomUUID());
        final List<InventoryEntryEntity> inventoryEntryEntities = Collections.nCopies(3, inventoryEntryEntity);
        when(inventoryEntryRepository.findAll()).thenReturn(inventoryEntryEntities);

        final List<InventoryEntryDto> inventoryEntryDtos = inventoryService.getAllInventoryEntries();

        List<InventoryEntryDto> actualDtos = inventoryEntryEntities.stream()
                .map(inventoryEntryEntityMapper::toDto)
                .collect(Collectors.toList());
        assertEquals(actualDtos, inventoryEntryDtos);
    }

    @Test
    void testGetAllInventoryEntries_worksWithSingleEntry() {
        final InventoryEntryEntity inventoryEntryEntity = TestHelper.createInventoryEntryEntity(UUID.randomUUID());
        final List<InventoryEntryEntity> inventoryEntryEntities = Collections.singletonList(inventoryEntryEntity);
        when(inventoryEntryRepository.findAll()).thenReturn(inventoryEntryEntities);

        final List<InventoryEntryDto> inventoryEntryDtos = inventoryService.getAllInventoryEntries();

        List<InventoryEntryDto> actualDtos =
                Collections.singletonList(inventoryEntryEntityMapper.toDto(inventoryEntryEntity));
        assertEquals(actualDtos, inventoryEntryDtos);
    }

    @Test
    void testGetAllInventoryEntries_worksWhenEmpty() {
        when(inventoryEntryRepository.findAll()).thenReturn(new ArrayList<>());

        final List<InventoryEntryDto> inventoryEntryDtos = inventoryService.getAllInventoryEntries();
        assertEquals(new ArrayList<>(), inventoryEntryDtos);
    }

    @Test
    void testCreateInventoryEntry_works() {
        final UUID uuid = UUID.randomUUID();
        final InventoryEntryDto inventoryEntryDto = TestHelper.createInventoryEntryDto(uuid);
        final InventoryEntryEntity inventoryEntryEntity = inventoryEntryEntityMapper.toEntity(inventoryEntryDto);

        when(inventoryEntryRepository.save(any(InventoryEntryEntity.class))).thenReturn(inventoryEntryEntity);

        final InventoryEntryEntity returnEntity = inventoryService.createInventoryEntry(inventoryEntryDto);
        assertEquals(inventoryEntryEntity, returnEntity);
    }

    @Test
    void testUpdateInventoryEntry_works() {
        final UUID uuid = UUID.randomUUID();
        final Integer quantity = 50;
        final String originCode = "CDC";
        final Boolean isSellable = false;
        final UpdateInventoryEntryDto updateInventoryEntryDto =
                TestHelper.createUpdateInventoryEntryDto(quantity, originCode, isSellable);
        final InventoryEntryEntity inventoryEntryEntity = TestHelper.createInventoryEntryEntity(uuid);

        when(inventoryEntryRepository.findById(uuid)).thenReturn(Optional.of(inventoryEntryEntity));
        when(inventoryEntryRepository.save(any(InventoryEntryEntity.class))).thenAnswer(returnsFirstArg());

        final InventoryEntryEntity returnEntity = inventoryService.updateInventoryEntry(uuid, updateInventoryEntryDto);

        verify(inventoryEntryRepository).save(any(InventoryEntryEntity.class));

        assertEquals(quantity, returnEntity.getQuantity());
        assertEquals(originCode, returnEntity.getOriginCode());
        assertEquals(isSellable, returnEntity.getIsSellable());
    }

    @Test
    void testUpdateInventoryEntry_throwsErrorWhenNotFound() {
        when(inventoryEntryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InventoryEntityNotFoundException.class,
                () -> inventoryService.updateInventoryEntry(UUID.randomUUID(), null));
    }

    @Test
    void testDeleteInventoryEntry_works() {
        final UUID uuid = UUID.randomUUID();
        final InventoryEntryEntity inventoryEntryEntity = TestHelper.createInventoryEntryEntity(uuid);

        when(inventoryEntryRepository.findById(uuid)).thenReturn(Optional.of(inventoryEntryEntity));

        inventoryService.deleteInventoryEntry(uuid);

        verify(inventoryEntryRepository).deleteById(uuid);
    }

    @Test
    void testDeleteInventoryEntry_throwsErrorWhenNotFound() {
        when(inventoryEntryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(InventoryEntityNotFoundException.class,
                () -> inventoryService.deleteInventoryEntry(UUID.randomUUID()));
    }
}
