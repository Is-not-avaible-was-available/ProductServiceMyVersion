package dev.rajat.ProductServiceMyVersion;

import dev.rajat.ProductServiceMyVersion.Controllers.ProductController;
import dev.rajat.ProductServiceMyVersion.Models.Category;
import dev.rajat.ProductServiceMyVersion.Models.Price;
import dev.rajat.ProductServiceMyVersion.Models.Product;
import dev.rajat.ProductServiceMyVersion.Repositories.CategoryRepository;
import dev.rajat.ProductServiceMyVersion.Repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceMyVersionApplication implements CommandLineRunner {
	private ProductController productController;
	private CategoryRepository categoryRepository;
	private ProductRepository productRepository;
	public ProductServiceMyVersionApplication(ProductController productController,
											  CategoryRepository categoryRepository,
											  ProductRepository productRepository){
		this.productController = productController;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceMyVersionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



	}
}
