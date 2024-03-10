package com.pivotree.ecom.Dto;

import com.pivotree.ecom.Model.Location;
import com.pivotree.ecom.Model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    private String locationReference;
    private String locationName;
    private Long totalCapacity;
    private Product product;
    private Location location;
}
