package com.pivotree.ecom.Repository;

import com.pivotree.ecom.Model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDBmanager {
    @Autowired
    private CategoryRepo catRepo;

    @PersistenceContext
    private EntityManager catManager;

    public void save(Category cat) {
        catManager.persist(cat);
    }

    public Category getById(Long id) {
        return catRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        catRepo.deleteById(id);
    }

    public List<Category> findAll() {
        return catRepo.findAll();
    }
}
