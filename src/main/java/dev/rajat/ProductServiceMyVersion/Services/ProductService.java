package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    public GenericProductDTO getProductById(Long id) throws NotFoundException;
    public List<GenericProductDTO> getAllProducts() throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO);
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException;
    public GenericProductDTO updateProductById(Long id, GenericProductDTO updatedProductDTO);
}
