package dev.rajat.ProductServiceMyVersion.Controllers;

import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{uuid}")
    public Category getCategoryById(@PathVariable("uuid") String uuid) throws NotFoundException {
        return categoryService.getCategoryById(uuid);
    }
    @GetMapping
    public List<Category> getAllCategory(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/products")
    public List<Product> getProductBasedOnCategories(String categoryId) throws NotFoundException {
        return categoryService.getProductBasedOnCategories(categoryId);
    }
}
