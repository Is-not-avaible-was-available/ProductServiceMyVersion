package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public GenericProductDTO productToGenericProductDTO(Product product){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        if(product.getUuid()!=null){
            genericProductDTO.setId(product.getUuid().toString());
        }
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setPrice(product.getPrice().getPrice());
        return genericProductDTO;
    }

    public Product genericProductDTOtoProduct(GenericProductDTO genericProductDTO){
        Product product = new Product();
        product.setUuid(UUID.fromString(genericProductDTO.getId()));
       product.setImage(genericProductDTO.getImage());
       product.setDescription(genericProductDTO.getDescription());
       product.setTitle(genericProductDTO.getTitle());
       return product;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO) {

        Product product = genericProductDTOtoProduct(newProductDTO);
        Product savedProduct = productRepository.save(product);
        return productToGenericProductDTO(savedProduct);
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO>genericProductDTOS = new ArrayList<>();
        for(Product product:products){
            genericProductDTOS.add(productToGenericProductDTO(product));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        Optional<Product>productOptional = productRepository.findById(UUID.fromString(id));
        if(productOptional.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = productOptional.get();
        return productToGenericProductDTO(product);
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProductDTO) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(UUID.fromString(id));
        if(productOptional.isEmpty()){
            throw  new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = productOptional.get();
        product.setTitle(updatedProductDTO.getTitle());
        product.setDescription(updatedProductDTO.getDescription());
        product.setImage(updatedProductDTO.getImage());
        return productToGenericProductDTO(product);
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("not found with id:"+id);
        }else{
            productRepository.deleteById(UUID.fromString(id));
        }
        Product product = optionalProduct.get();
        return productToGenericProductDTO(product);
    }
}
