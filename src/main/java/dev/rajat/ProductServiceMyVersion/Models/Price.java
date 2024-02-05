package dev.rajat.ProductServiceMyVersion.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Price extends BaseModel{
    private double price;
    private String currency;
}
