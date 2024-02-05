package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.GenericProductDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Price;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
import dev.rajat.ProductServiceMyVersion.Repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {

    @Autowired
    private SelfProductService selfProductService;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        Product product = new Product();
        product.setUuid(UUID.fromString("42c8c9f3-066b-4513-8065-9e8294a7c26e"));
        product.setTitle("Test-Product");
        product.setImage("www.test.com");
        Category category = new Category();
        category.setName("Electronics");
        product.setCategory(category);
        Price price =  new Price();
        price.setPrice(10000.0);
        price.setCurrency("Rupee");
        product.setPrice(price);
        product.setDescription("Best Phone ever!");
        Optional<Product> optionalProduct = Optional.of(product);

        when(productRepository.findById(any(UUID.class))).thenReturn(optionalProduct);

        GenericProductDTO response = selfProductService.getProductById("42c8c9f3-066b-4513-8065-9e8294a7c26e");

        Assertions.assertEquals("42c8c9f3-066b-4513-8065-9e8294a7c26e", response.getId());
        Assertions.assertEquals("Test-Product", response.getTitle());
        Assertions.assertEquals("www.test.com", response.getImage());
    }

    @Test
    public void testGetProductByIdThrowsNotFoundExceptionWhenOptionalIsNull(){
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, ()->{
            selfProductService.getProductById("42c8c9f3-066b-4513-8065-9e8294a7c26e");
        });
    }
}
