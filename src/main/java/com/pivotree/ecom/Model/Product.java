package com.pivotree.ecom.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
//    ProductID (Primary Key)
//-	ProductReference
//-	Description
//-	Price
//-	Brand
//-	ManufacturingDate
//-	ExpiryDate
//-	CategoryID (Foreign Key referencing Category)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productReference",unique = true)
    private String productReference;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "manu_date")
    @Temporal(TemporalType.DATE)
    private Date manufactureDate;

    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_dim_id")
    private Dimensions dims;

    @ManyToOne
    @JoinColumn(name = "fk_cat_id")
    @JsonBackReference
    private Category cat;

    @OneToMany(mappedBy = "productId",cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Inventory> inventories= new ArrayList<>();

    public Product(String productReference, String description, Long price, String brand, Date manufactureDate, Date expiryDate, Dimensions dims, Category cat) {
        this.productReference = productReference;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.dims = dims;
        this.cat=cat;
    }
}
