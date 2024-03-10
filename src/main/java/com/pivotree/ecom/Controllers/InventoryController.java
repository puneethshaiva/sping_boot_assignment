package com.pivotree.ecom.Controllers;

import com.pivotree.ecom.Dto.CategoryDto;
import com.pivotree.ecom.Model.Category;
import com.pivotree.ecom.Model.Inventory;
import com.pivotree.ecom.Model.Product;
import com.pivotree.ecom.Services.InventoryService;
import com.pivotree.ecom.intemplate.InventoryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService service;
    @PostMapping("/create")
    ResponseEntity<Object> createInven(@RequestBody InventoryInput inv){
        Inventory newInv=service.createInv(inv);
        return new ResponseEntity<>(newInv, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/getAll")
    ResponseEntity<Object> getAllInv(){
        List<Inventory> inventories=service.getAllInvs();
        return new ResponseEntity<>(inventories,HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<Object> getInvById(@PathVariable String id){
        Inventory inv=service.getInv(id);
        if (Objects.isNull(inv)){
            return new ResponseEntity<>("inventory entry not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>(inv,HttpStatusCode.valueOf(200));
        }
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteByid(@PathVariable String id){
        service.deleteByid(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
