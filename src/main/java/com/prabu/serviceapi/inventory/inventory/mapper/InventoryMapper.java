package com.prabu.serviceapi.inventory.inventory.mapper;

import com.prabu.serviceapi.inventory.category.Category;
import com.prabu.serviceapi.inventory.inventory.Inventory;
import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDTO InventoryToInventoryDTO(Inventory inventory);

    @Mapping(target = "id", source = "inventoryCreateDTO.id")
    @Mapping(target = "category", source="category")
    Inventory InventoryDTOToInventory(InventoryCreateDTO inventoryCreateDTO, Category category);

}
