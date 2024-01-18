package dev.rajat.ProductServiceMyVersion.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class GenericProductDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;
}
