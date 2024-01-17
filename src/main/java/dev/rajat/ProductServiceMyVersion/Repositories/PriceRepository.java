package dev.rajat.ProductServiceMyVersion.Repositories;

import dev.rajat.ProductServiceMyVersion.Models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
}
