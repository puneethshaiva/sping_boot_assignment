package com.pivotree.ecom.Repository;

import com.pivotree.ecom.Model.Location;
import com.pivotree.ecom.Model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationDBmanager {
    @Autowired
    private LocationRepo locationRepo;

    @PersistenceContext
    private EntityManager locationManager;

    public void save(Location loc) {
        locationManager.persist(loc);
    }

    public Location getById(Long id) {
        return locationRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        locationRepo.deleteById(id);
    }

    public List<Location> findAll() {
        return locationRepo.findAll();
    }
}
