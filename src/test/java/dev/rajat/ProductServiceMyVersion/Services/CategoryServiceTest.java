package dev.rajat.ProductServiceMyVersion.Services;

import dev.rajat.ProductServiceMyVersion.DTOs.CategoryDTO;
import dev.rajat.ProductServiceMyVersion.Exceptions.NotFoundException;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
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
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        Category category = new Category();
        category.setUuid(UUID.fromString("42c8c9f3-066b-4513-8065-9e8294a7c26e"));
        category.setName("Food");

        Optional<Category> categoryOptional = Optional.of(category);
        when(categoryRepository.findById(any(UUID.class))).thenReturn(categoryOptional);

        CategoryDTO response = categoryService.getCategoryById("42c8c9f3-066b-4513-8065-9e8294a7c26e");

        Assertions.assertEquals("42c8c9f3-066b-4513-8065-9e8294a7c26e", response.getId());
        Assertions.assertEquals("Food", response.getName());

    }

    @Test
    public void testGetProductByIdThrowsNotFoundExceptionWhenRepositoryReturnsNull(){
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, ()->{
            categoryService.getCategoryById("42c8c9f3-066b-4513-8065-9e8294a7c26e");
        });
    }
}
