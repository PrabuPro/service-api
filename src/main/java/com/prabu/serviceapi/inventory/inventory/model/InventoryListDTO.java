package com.prabu.serviceapi.inventory.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InventoryListDTO {

    private List<InventoryDTO> inventories;

}
