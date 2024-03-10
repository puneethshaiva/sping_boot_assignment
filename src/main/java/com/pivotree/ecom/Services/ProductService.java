package com.pivotree.ecom.Services;

import com.pivotree.ecom.Dto.ProductDto;
import com.pivotree.ecom.Model.Category;
import com.pivotree.ecom.Model.Dimensions;
import com.pivotree.ecom.Model.Product;
import com.pivotree.ecom.Repository.ProductDBmanager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductDBmanager dbManager;

    @Transactional(rollbackOn = Exception.class,value = Transactional.TxType.REQUIRED)
    public Product createProd(ProductDto prod) {
        Product newProd=new Product(prod.getProductReference(), prod.getDescription(), prod.getPrice(), prod.getBrand(),
                prod.getManufactureDate(),prod.getExpiryDate(), new Dimensions(prod.getDims().getLength(),prod.getDims()
                .getHeight(),prod.getDims().getWidth()),prod.getCat());
        dbManager.save(newProd);
        return newProd;
    }

    public List<Product> getAllProds() {
        return dbManager.findAll();
    }

    public Product getProd(String id) {
        return dbManager.getById(Long.parseLong(id));
    }

    @Transactional
    public Optional<Product> update(String id, ProductDto productDto) {
        Product fetched= dbManager.getById(Long.parseLong(id));
        if (Objects.isNull(fetched)){
            return Optional.empty();
        }else {
            Dimensions dims=productDto.getDims();
            Category cat=productDto.getCat();
            fetched.setProductReference(productDto.getProductReference());
            fetched.setDescription(productDto.getDescription());
            fetched.setBrand(productDto.getBrand());
            fetched.setExpiryDate(productDto.getExpiryDate());
            fetched.setManufactureDate(productDto.getManufactureDate());
            fetched.setPrice(productDto.getPrice());
            fetched.setDims(new Dimensions(dims.getLength(), dims.getHeight(), dims.getWidth()));
            if (!Objects.isNull(cat))
                fetched.setCat(new Category(cat.getId(), cat.getName(), cat.getDescription()));
            //

            //
            dbManager.save(fetched);
        }
        return Optional.of(fetched);
    }

    public void deleteByid(String id) {
        dbManager.delete(Long.parseLong(id));
    }
}
