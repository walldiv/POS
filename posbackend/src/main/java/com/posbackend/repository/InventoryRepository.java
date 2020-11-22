package com.posbackend.repository;

import com.posbackend.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItem, String> {
    InventoryItem findByupc(String upc);
}
