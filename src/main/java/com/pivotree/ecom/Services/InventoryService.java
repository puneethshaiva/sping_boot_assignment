package com.pivotree.ecom.Services;

import com.pivotree.ecom.Model.Inventory;
import com.pivotree.ecom.Model.Location;
import com.pivotree.ecom.Model.Product;
import com.pivotree.ecom.Repository.*;
import com.pivotree.ecom.intemplate.InventoryInput;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryDBmanager dbManager;
    @Autowired
    private LocationDBmanager locationDBmanager;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private LocationRepo locationRepo;

    @Transactional(rollbackOn = Exception.class,value = Transactional.TxType.REQUIRED)
    public Inventory createInv(InventoryInput inv) {
        Product prod=productRepo.findByReference(inv.getProduct());
        Location loc=locationRepo.findByReference(inv.getLocation());
        Inventory newInv=new Inventory(inv.getLocation(),inv.getProduct(),loc,prod,inv.getQuantity());
        loc=locationService.getloc(String.valueOf(loc.getId()));
        long rem=loc.getRemainingCapacity()-(prod.getDims().getVol()*inv.getQuantity());
        if (rem<0 || inv.getQuantity()<=0){
            throw new RuntimeException();
        }
        loc.setRemainingCapacity(rem);
        dbManager.save(newInv);
        locationDBmanager.save(loc);
        return newInv;
    }

    public List<Inventory> getAllInvs() {
        return dbManager.getAll();
    }

    public Inventory getInv(String id) {
        return dbManager.getById(Long.parseLong(id));
    }

    public void deleteByid(String id) {
        Optional<Inventory> inv= Optional.ofNullable(dbManager.getById(Long.parseLong(id)));
        if (inv.isEmpty()) return;
        Inventory deleteInv=inv.get();
        Long residual=(productService.getProd(String.valueOf(deleteInv.getProductId())).getDims().getVol())*deleteInv
                .getQuantity();
        Location loc=locationDBmanager.getById(Long.parseLong(id));
        loc.setRemainingCapacity(loc.getRemainingCapacity()+residual);
        locationDBmanager.save(loc);
        dbManager.delete(Long.parseLong(id));
    }
}
