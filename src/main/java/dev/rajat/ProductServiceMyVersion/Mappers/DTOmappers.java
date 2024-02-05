package dev.rajat.ProductServiceMyVersion.Mappers;

import dev.rajat.ProductServiceMyVersion.DTOs.CategoryDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Price;
import dev.rajat.ProductServiceMyVersion.Models.Product;

public class DTOmappers {

    public static CategoryDTO categoryToCategoryDTO(Category category){
        if(category==null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getUuid().toString());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static GenericProductDTO productToGenericProductDto(Product product){
        if(product == null){
            return null;
        }
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(product.getUuid().toString());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setPrice(product.getPrice().getPrice());
        genericProductDTO.setDescription(product.getDescription());
        return genericProductDTO;
    }
}
