package com.pivotree.ecom.intemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInput {
    private String product;
    private String location;
    private Long quantity;
}
