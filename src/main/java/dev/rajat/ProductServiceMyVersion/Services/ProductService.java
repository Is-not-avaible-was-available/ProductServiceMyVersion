package dev.rajat.ProductServiceMyVersion.Services;


import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO getProductById(String id) throws NotFoundException;
    public List<GenericProductDTO> getAllProducts() throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    public GenericProductDTO deleteProductById(String id) throws NotFoundException;

    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProduct) throws NotFoundException;
}
