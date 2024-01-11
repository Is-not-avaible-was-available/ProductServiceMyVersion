package dev.rajat.ProductServiceMyVersion.ProductController;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();

    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProduct(@PathVariable("id")Long id, @RequestBody GenericProductDTO updatedProductDTO){
        return productService.updateProductById(id,updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        return productService.deleteProductById(id);

    }
}
