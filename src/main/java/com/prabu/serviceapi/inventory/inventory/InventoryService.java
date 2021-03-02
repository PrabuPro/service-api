package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    List<InventoryDTO> getInventoryList();

    Page<InventoryDTO> getInventoryGrid(PaginationPage paginationPage);

    InventoryDTO saveInventory(InventoryCreateDTO inventoryCreateDTO);

    InventoryDTO updateInventory(Long id, InventoryCreateDTO inventoryCreateDTO);

    void deleteInventory(Long id);

}
