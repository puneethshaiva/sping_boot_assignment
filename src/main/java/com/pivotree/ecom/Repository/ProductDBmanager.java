package com.pivotree.ecom.Repository;

import com.pivotree.ecom.Model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDBmanager {
    @Autowired
    private ProductRepo productRepo;

    @PersistenceContext
    private EntityManager productManager;

    public void save(Product prod) {
        productManager.persist(prod);
    }

    public Product getById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
