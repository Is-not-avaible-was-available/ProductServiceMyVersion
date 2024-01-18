package dev.rajat.ProductServiceMyVersion.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Category extends BaseModel{

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;
    private String name;
}
