package com.pivotree.ecom.Services;

import com.pivotree.ecom.Dto.CategoryDto;
import com.pivotree.ecom.Model.Category;
import com.pivotree.ecom.Repository.CategoryDBmanager;
import com.pivotree.ecom.Repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryDBmanager dbManager;
    @Transactional(rollbackOn = Exception.class,value = Transactional.TxType.REQUIRED)
    public Category createCat(CategoryDto cat) {
        Category newCat=new Category(cat.getName(), cat.getDescription());
        dbManager.save(newCat);
        return newCat;
    }

    public Category getCat(String id) {
        return dbManager.getById(Long.parseLong(id));
    }


    @Transactional
    public Optional<Category> update(String id, CategoryDto cat) {
        Category update=dbManager.getById(Long.parseLong(id));
        if (Objects.isNull(update)) {
            return Optional.empty();
        }else {
            update.setName(cat.getName());
            update.setDescription(cat.getDescription());
            dbManager.save(update);
        }
        return Optional.of(update);
    }

    public List<Category> getAllCat() {
        return dbManager.findAll();
    }

    public void deleteByid(String id) {
        dbManager.delete(Long.parseLong(id));
    }
}
