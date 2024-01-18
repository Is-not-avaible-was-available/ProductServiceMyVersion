package dev.rajat.ProductServiceMyVersion.Repositories;

import dev.rajat.ProductServiceMyVersion.Models.Product;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
//    public Product findByTitleAndPrice_currency(String title, String currency);
//    @Query(value = SQLQueries.productQuery, nativeQuery = true)
//    public Product findByTitle(String title);
//    @Query(value = "select Product  from Product where Product.title=:title", nativeQuery = false)
//    public Product findByTitle2(String title);
}
