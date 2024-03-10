package com.pivotree.ecom.Model;

import jakarta.persistence.*;
import lombok.*;

//DimensionID (Primary Key)
//-	Length
//-	Width
//-	Height
//-	Weight
//-	Volume
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dims")
public class Dimensions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "length")
    private Long length;

    @Column(name = "height")
    private Long height;

    @Column(name = "width")
    private Long width;

    @Column(name = "vol")
    private Long vol;

    public Dimensions(Long length, Long height, Long width) {
        this.length = length;
        this.height = height;
        this.width = width;
        this.vol=length*height*width;
    }
}
