package com.net.myappi.application.port.out;

import com.net.myappi.domain.db.Product;

import java.util.List;

public interface ProductPortOut {
    public void create(Product productRequestDto) throws RuntimeException;
    public void updateAll(Product productRequestDto,String correlationId) throws RuntimeException;
    public void updatePartially( Product productRequestDto,String correlationId) throws RuntimeException;
    public void delete( Product productRequestDto,String correlationId) throws RuntimeException;
    public Product getById(String correlationId) throws RuntimeException;
    public List<Product> getAll() throws RuntimeException;
    public List<Product> getByName(String name) throws RuntimeException;
}
