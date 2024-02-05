package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.CategoryDTO;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.AlreadyExistsException;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Mappers.DTOmappers;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("categoryService")
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }


    public CategoryDTO getCategoryById(String id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }
        Category category = optionalCategory.get();
        return DTOmappers.categoryToCategoryDTO(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws AlreadyExistsException {
        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(categoryDTO.getName());
        if(categoryOptional.isPresent()){
            throw new AlreadyExistsException("Category with name:"+categoryDTO.getName()+" already exists!");
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        Category savedCategory = categoryRepository.save(category);
        return DTOmappers.categoryToCategoryDTO(savedCategory);
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category>categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category: categories){
            categoryDTOS.add(DTOmappers.categoryToCategoryDTO(category));
        }
        return categoryDTOS;
    }

    public CategoryDTO deleteCategoryById(String id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }
        Category category = categoryOptional.get();
        categoryRepository.deleteById(UUID.fromString(id));
        return DTOmappers.categoryToCategoryDTO(category);
    }

    public CategoryDTO updateCategoryById(String id, CategoryDTO categoryDTO)throws NotFoundException{
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }
        Category category = optionalCategory.get();
        if(categoryDTO.getName()!=null){
            category.setName(categoryDTO.getName());
        }
        if(categoryDTO.getId()!=null){
            category.setUuid(UUID.fromString(categoryDTO.getId()));
        }
        Category updatedCategory = categoryRepository.save(category);
        return DTOmappers.categoryToCategoryDTO(updatedCategory);
    }

    public List<GenericProductDTO> getAllProductsByCategory(String id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("category not found"+id);
        }
        Category category = optionalCategory.get();
        List<Product> products = category.getProducts();

        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Product product: products){
            genericProductDTOS.add(DTOmappers.productToGenericProductDto(product));
        }
        return genericProductDTOS;
    }

    public List<GenericProductDTO> getAllProductsByCategories(List<String> categoryIds){
        List<UUID> uuidCategories = categoryIds.stream().map(UUID::fromString).toList();
        List<Category> categories = categoryRepository.findAllById(uuidCategories);
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Category category: categories){
            for(Product product: category.getProducts()){
                genericProductDTOS.add(DTOmappers.productToGenericProductDto(product));
            }
        }
        return genericProductDTOS;
    }
}
