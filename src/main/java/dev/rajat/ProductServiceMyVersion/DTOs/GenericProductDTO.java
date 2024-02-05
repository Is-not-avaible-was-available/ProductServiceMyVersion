package dev.rajat.ProductServiceMyVersion.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
