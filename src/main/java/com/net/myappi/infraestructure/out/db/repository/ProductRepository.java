package com.net.myappi.infraestructure.out.db.repository;

import com.net.myappi.domain.db.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
