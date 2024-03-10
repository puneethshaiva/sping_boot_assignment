package com.pivotree.ecom.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "locationReference",unique = true)
    private String locationReference;

    @Column(name = "locationName")
    private String locationName;

    @Column(name = "totalCapacity")
    private Long totalCapacity;

    @Column(name = "remainingCapacity")
    private Long remainingCapacity;


    @OneToMany(mappedBy = "locationId",cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Inventory> inventories= new ArrayList<>();

    public Location(String locationReference, String locationName, Long totalCapacity,Long remainingCapacity) {
        this.locationReference = locationReference;
        this.locationName = locationName;
        this.totalCapacity = totalCapacity;
        this.remainingCapacity = remainingCapacity;
    }

}
/*
-	LocationID (Primary Key)
-	LocationReference (Unique)
-	LocationName
-	TotalCapacity
-	RemianingCapacity
*/