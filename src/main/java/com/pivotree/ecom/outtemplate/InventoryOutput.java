package com.pivotree.ecom.outtemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryOutput {
    private String product;
    private String location;
    private Long quantity;
}
