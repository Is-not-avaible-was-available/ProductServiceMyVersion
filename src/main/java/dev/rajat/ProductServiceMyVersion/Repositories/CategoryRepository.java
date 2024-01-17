package dev.rajat.ProductServiceMyVersion.Repositories;

import dev.rajat.ProductServiceMyVersion.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
