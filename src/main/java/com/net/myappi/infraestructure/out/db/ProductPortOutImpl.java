package com.net.myappi.infraestructure.out.db;

import com.net.myappi.application.port.out.ProductPortOut;
import com.net.myappi.domain.db.Product;
import com.net.myappi.infraestructure.out.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductPortOutImpl implements ProductPortOut {

    private final ProductRepository productRepository;
    @Override
    public void create(Product product) throws RuntimeException {
        log.info("[ProductPortOutImpl - Postgres Impl ] saving {} ....",product.getId());
        productRepository.save(product);
    }

    @Override
    public void updateAll(Product productRequestDto, String correlationId) throws RuntimeException {

    }

    @Override
    public void updatePartially(Product productRequestDto, String correlationId) throws RuntimeException {

    }

    @Override
    public void delete(Product productRequestDto, String correlationId) throws RuntimeException {

    }

    @Override
    public Product getById(String correlationId) throws RuntimeException {
        return null;
    }

    @Override
    public List<Product> getAll() throws RuntimeException {
        log.info("[ProductPortOutImpl - Postgres Impl ] get all data  ....");
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByName(String name) throws RuntimeException {
        return null;
    }
}
