package dev.rajat.ProductServiceMyVersion.Controllers;

import dev.rajat.ProductServiceMyVersion.DTOs.CategoryDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.AlreadyExistsException;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController{

    private CategoryService categoryService;

    @Autowired
    public CategoryController(@Qualifier("categoryService") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") String id) throws NotFoundException {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws AlreadyExistsException {
        return categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public CategoryDTO deleteCategoryById(@PathVariable("id") String id) throws NotFoundException {
        return categoryService.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategoryById(@PathVariable("id") String id,
                                          @RequestBody CategoryDTO categoryDTO) throws NotFoundException {
        return categoryService.updateCategoryById(id, categoryDTO);
    }

    @GetMapping("/products/{id}")
    public List<GenericProductDTO> getAllProductByCategory(@PathVariable("id") String id) throws NotFoundException {
        return categoryService.getAllProductsByCategory(id);
    }

    @GetMapping("/products")
    public List<GenericProductDTO> getAllProductsByCategories(@RequestBody List<String> categoriesId){
        return categoryService.getAllProductsByCategories(categoriesId);
    }
}
