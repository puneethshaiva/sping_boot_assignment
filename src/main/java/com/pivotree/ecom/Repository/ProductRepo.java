package com.pivotree.ecom.Repository;

import com.pivotree.ecom.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM products p WHERE p.product_reference=:productReference",nativeQuery = true)
    Product findByReference(String productReference);
}

/*

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT * FROM products WHERE price > :minPrice", nativeQuery = true)
  List<Product> findProductsAbovePrice(@Param("minPrice") double minPrice);
}

 */