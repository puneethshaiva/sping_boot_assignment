package com.pivotree.ecom.Repository;
import com.pivotree.ecom.Model.Inventory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryDBmanager {
    @Autowired
    private InventoryRepo inventoryRepo;

    @PersistenceContext
    private EntityManager InventoryDbManager;

    public void save(Inventory inv) {
        InventoryDbManager.persist(inv);
    }

    public List<Inventory> getAll() {
        return inventoryRepo.findAll();
    }

    public Inventory getById(Long id) {
        return inventoryRepo.findById(id).orElse(null);
    }

    public void delete(Long l) {
        inventoryRepo.deleteById(l);
    }
}
