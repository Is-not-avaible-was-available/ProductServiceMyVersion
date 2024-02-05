package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Mappers.DTOmappers;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Price;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
import dev.rajat.ProductServiceMyVersion.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(id));
        if(productOptional.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = productOptional.get();
        return DTOmappers.productToGenericProductDto(product);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(Product product: products){
            genericProductDTOS.add(DTOmappers.productToGenericProductDto(product));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Product product = new Product();
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());

        Optional<Category>optionalCategory = categoryRepository.findCategoryByName(genericProductDTO.getCategory());
        if(optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(genericProductDTO.getCategory());
            Category saved = categoryRepository.save(category);
            product.setCategory(saved);
        }else{
            Category existingCategory = optionalCategory.get();
            product.setCategory(existingCategory);
        }
        Price price = new Price();
        price.setPrice(genericProductDTO.getPrice());
        price.setCurrency("Rupee");
        product.setPrice(price);
        Product savedProduct = productRepository.save(product);
        return DTOmappers.productToGenericProductDto(savedProduct);
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(id));
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product with id:"+id+", is not found!");
        }
        productRepository.deleteById(UUID.fromString(id));
        Product product = productOptional.get();
        return DTOmappers.productToGenericProductDto(product);
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProduct) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("Product with id:"+id+", is not found!");
        }
        Product product = optionalProduct.get();
        if(updatedProduct.getTitle()!=null){
            product.setTitle(updatedProduct.getTitle());
        }
        if(updatedProduct.getImage()!=null){
            product.setImage(updatedProduct.getImage());
        }

        if(updatedProduct.getDescription()!=null){
            product.setDescription(updatedProduct.getDescription());
        }

        if(updatedProduct.getCategory()!=null){
            Optional<Category>optionalCategory = categoryRepository
                    .findCategoryByName(updatedProduct.getCategory());
            if(optionalCategory.isEmpty()){
                Category category = new Category();
                category.setName(updatedProduct.getCategory());
                Category saved = categoryRepository.save(category);
                product.setCategory(saved);
            }else{
                product.setCategory(optionalCategory.get());
            }
        }

        Double newPrice = updatedProduct.getPrice();
        if(!newPrice.isNaN()){
            Price price = new Price();
            price.setPrice(updatedProduct.getPrice());
            price.setCurrency("Rupee");
            product.setPrice(price);
        }

        Product newProduct = productRepository.save(product);
        return DTOmappers.productToGenericProductDto(newProduct);
    }
}
