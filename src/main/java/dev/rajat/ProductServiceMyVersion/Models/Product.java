package dev.rajat.ProductServiceMyVersion.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{
    private String title;
    private String description;
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Price price;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    private String image;
}
