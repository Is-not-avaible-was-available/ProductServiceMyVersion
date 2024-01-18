package dev.rajat.ProductServiceMyVersion.Services;

import com.sun.jdi.LongValue;
import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService{

    public GenericProductDTO getProductById(String id) throws NotFoundException;


    public List<GenericProductDTO> getAllProducts() throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO);
    public GenericProductDTO deleteProductById(String id) throws NotFoundException;
    public GenericProductDTO updateProductById(String id, GenericProductDTO updatedProductDTO) throws NotFoundException;
}
