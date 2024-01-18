package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(String id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }

        return categoryOptional.get();
    }
    @Transactional
    public List<Product> getProductBasedOnCategories(String categoryId) throws NotFoundException {
//       List<UUID>catUuids = categoryIds.stream().map(UUID::fromString).toList();



      Optional<Category>categoryOptional = categoryRepository.findById(UUID.fromString(categoryId));
      if(categoryOptional.isEmpty()){
          throw new NotFoundException("category not found!");
      }
      Category category= categoryOptional.get();
        List<Product> products = new ArrayList<>();

            for(Product product: category.getProducts()){
                products.add(product);
            }

        return products;
    }

}
