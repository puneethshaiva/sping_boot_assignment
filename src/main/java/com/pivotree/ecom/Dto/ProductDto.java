package com.pivotree.ecom.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pivotree.ecom.Model.Category;
import com.pivotree.ecom.Model.Dimensions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String productReference;
    private String description;
    private Long price;
    private String brand;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date manufactureDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    private Dimensions dims;
    private Category cat;
}
