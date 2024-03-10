package com.pivotree.ecom.Controllers;

import com.pivotree.ecom.Dto.CategoryDto;
import com.pivotree.ecom.Model.Category;
import com.pivotree.ecom.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping("/create")
    ResponseEntity<Object> createCat(@RequestBody CategoryDto cat){
        Category newCat=service.createCat(cat);
        Category temp= new Category(newCat.getId(), cat.getName(),cat.getDescription(),null);
        return new ResponseEntity<>(temp, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<Object> getCat(@PathVariable String id){
        Category cat=service.getCat(id);
        if (Objects.isNull(cat)) {
            return new ResponseEntity<>("category not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>(cat,HttpStatusCode.valueOf(200));
        }
    }

    @PutMapping("/updateById/{id}")
    ResponseEntity<Object> updateCatById(@PathVariable String id,@RequestBody CategoryDto cat){
        Optional<Category> fetchCat=service.update(id,cat);
        if (fetchCat.isEmpty()) {
            return new ResponseEntity<>("Category Not found",HttpStatusCode.valueOf(404));
        }else {
            return new ResponseEntity<>("Category with id:"+id+" updated",HttpStatusCode.valueOf(200));
        }
    }

    @GetMapping("/getAll")
    ResponseEntity<Object> getAllCats(){
        List<Category> cats=service.getAllCat();
        return new ResponseEntity<>(cats,HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Object> deleteByid(@PathVariable String id){
        service.deleteByid(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
