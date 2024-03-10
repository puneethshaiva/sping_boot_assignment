package com.pivotree.ecom.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "inventory",uniqueConstraints = {@UniqueConstraint(columnNames = {"locationId","productId"}),
@UniqueConstraint(columnNames = {"locationReference","productReference"})})
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locationReference")
    private String locationReference;
    @Column(name = "productReference")
    private String productReference;

    @ManyToOne()
    @JoinColumn(name ="locationId")
    @JsonBackReference
    private Location locationId;

    @ManyToOne()
    @JoinColumn(name = "productId")
    @JsonBackReference
    private Product productId;

    @Column(name = "quantity")
    private Long quantity;

    public Inventory(Location locationId, Product productId, Long quantity) {
        this.locationId = locationId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Inventory(String locationReference, String productReference, Location locationId, Product productId, Long quantity) {
        this.locationReference = locationReference;
        this.productReference = productReference;
        this.locationId = locationId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
