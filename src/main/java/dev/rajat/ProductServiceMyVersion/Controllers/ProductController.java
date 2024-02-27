package dev.rajat.ProductServiceMyVersion.Controllers;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Security.JWTData;
import dev.rajat.ProductServiceMyVersion.Security.ValidateToken;
import dev.rajat.ProductServiceMyVersion.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private ValidateToken validateToken;
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService,
                             ValidateToken validateToken){
        this.productService = productService;
        this.validateToken = validateToken;
   }
    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
            @PathVariable("id") String id) throws NotFoundException {
        Optional<JWTData> jwtToken = validateToken.validateToken(authToken);
        if(jwtToken.isPresent()){
            // do whatever that needs to be done according to the business logic
            JWTData jwtData = jwtToken.get();
            List<String> roles = jwtData.getRoles();
            String email = jwtData.getEmail();
        }
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
    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.deleteProductById(id);
    }
    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") String id,
                                               @RequestBody GenericProductDTO updatedProduct) throws NotFoundException {
        return productService.updateProductById(id, updatedProduct);
    }
}
