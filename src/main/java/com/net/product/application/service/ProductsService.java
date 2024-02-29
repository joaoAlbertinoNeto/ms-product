package com.net.product.application.service;

import com.net.product.domain.dto.rest.ProductRequestDto;
import com.net.product.domain.dto.rest.ProductResponseDto;
import org.hibernate.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    public ProductResponseDto create(ProductRequestDto productRequestDto) throws RuntimeException;
    public ProductResponseDto updateAll( ProductRequestDto productRequestDto,String correlationId) throws RuntimeException;
    public ProductResponseDto updatePartially( ProductRequestDto productRequestDto,String correlationId) throws RuntimeException;
    public void delete(String correlationId) throws RuntimeException;
    public ProductResponseDto getById(String correlationId) throws RuntimeException;

    public Optional<List<ProductResponseDto>> getAll() throws RuntimeException;
    public Optional<List<ProductResponseDto>>getByName(String name) throws ObjectNotFoundException;
}
