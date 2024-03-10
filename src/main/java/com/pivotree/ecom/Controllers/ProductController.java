package com.pivotree.ecom.Controllers;

import com.pivotree.ecom.Dto.ProductDto;
import com.pivotree.ecom.Model.Product;
import com.pivotree.ecom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @PostMapping("/create")
    ResponseEntity<Object> createProduct(@RequestBody ProductDto prod){
        Product newProd=service.createProd(prod);
        return new ResponseEntity<>(newProd, HttpStatusCode.valueOf(201));
    }
    @GetMapping("/getAll")
    ResponseEntity<Object> getAllProd(){
        List<Product> prods=service.getAllProds();
        return new ResponseEntity<>(prods,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/getById/{id}")
    ResponseEntity<Object> getProdById(@PathVariable String id){
        Product prod=service.getProd(id);

        if (Objects.isNull(prod)){
            return new ResponseEntity<>("product not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>(prod,HttpStatusCode.valueOf(200));
        }
    }

    @PutMapping("/updateById/{id}")
    ResponseEntity<Object> updateProdById(@PathVariable String id, @RequestBody ProductDto productDto){
        Optional<Product> fetched=service.update(id,productDto);
        if (fetched.isEmpty()){
            return new ResponseEntity<>("product not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>("updated product id: "+id,HttpStatusCode.valueOf(200));
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteByid(@PathVariable String id){
        service.deleteByid(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
