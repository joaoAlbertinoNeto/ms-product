package com.net.myappi.infraestructure.out.db;

import com.net.myappi.application.port.out.ProductPortOut;
import com.net.myappi.domain.db.Product;
import com.net.myappi.infraestructure.out.db.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void updateAll(Product product, String correlationId) throws RuntimeException {
        log.info("[ProductPortOutImpl - Postgres Impl ] Updating all {} ....",product.getId());
        delete(correlationId);
        create(product);

    }

    @Override
    public void updatePartially(Product product, String correlationId) throws RuntimeException {

    }

    @Transactional
    @Override
    public void delete(String correlationId) throws RuntimeException {
        try{
            Product savedProduct = getById(correlationId);
            log.info("[ProductPortOutImpl - Postgres Impl ] deleting {} ....",savedProduct.getId());
            productRepository.delete(savedProduct);
        } catch (ObjectNotFoundException ex){
            log.error("[ProductPortOutImpl - Postgres Impl ] ERROR : {} ",ex.getMessage());
            throw ex;
        }
    }

    @Override
    public Product getById(String correlationId) throws ObjectNotFoundException {
        return productRepository.findByCorrelationId(correlationId).orElseThrow(() -> new ObjectNotFoundException(new Object(),""));
    }

    @Override
    public List<Product> getAll() throws RuntimeException {
        log.info("[ProductPortOutImpl - Postgres Impl ] get all data  ....");
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByName(String name) throws RuntimeException {
        return productRepository.findAllByName(name);
    }
}
