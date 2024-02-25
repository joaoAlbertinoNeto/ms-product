package com.net.myappi.infraestructure.out.db.repository;

import com.net.myappi.domain.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Product getByCorrelationId(String correlationId);

    public Optional<Product> findByCorrelationId(String correlationId);

}
