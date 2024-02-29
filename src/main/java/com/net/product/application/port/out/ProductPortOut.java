package com.net.product.application.port.out;

import com.net.product.domain.db.Product;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

public interface ProductPortOut {
    public void create(Product product) throws RuntimeException;
    public void updateAll(Product product,String correlationId) throws RuntimeException;
    public void updatePartially( Product product,String correlationId) throws RuntimeException;
    public void delete(String correlationId) throws ObjectNotFoundException;
    public Product getById(String correlationId) throws ObjectNotFoundException;
    public List<Product> getAll() throws RuntimeException;
    public List<Product> getByName(String name) throws RuntimeException;
}
