package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryListDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(InventoryController.API_V_1_INVENTORY)
public class InventoryController {
    public static final String API_V_1_INVENTORY = "/api/v1/inventory";

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<InventoryListDTO> getInventoryList(){
        return new ResponseEntity<>(new InventoryListDTO(inventoryService.getInventoryList()), HttpStatus.OK);
    }

    @GetMapping("/grid")
    public ResponseEntity<Page<InventoryDTO>> getInventoryGrid(PaginationPage paginationPage){
        return new ResponseEntity<>(inventoryService.getInventoryGrid(paginationPage), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<InventoryDTO> saveInventory(@Valid @RequestBody InventoryCreateDTO inventoryCreateDTO){
        return new ResponseEntity<>(inventoryService.saveInventory(inventoryCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryCreateDTO inventoryCreateDTO){
        return new ResponseEntity<>(inventoryService.updateInventory(id, inventoryCreateDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id){
        inventoryService.deleteInventory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
