package dev.rajat.ProductServiceMyVersion.Repositories;

import dev.rajat.ProductServiceMyVersion.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

     Page<Product> findAllByTitleContaining(String query, Pageable pageable);
}
