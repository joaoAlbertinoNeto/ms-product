package com.net.myappi.application.port.in;

import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.web.client.HttpServerErrorException.InternalServerError;

public interface ProductPortIn {
    public ResponseEntity<ProductResponseDto> create(ProductRequestDto productRequestDto) throws InternalServerError;
    public ProductResponseDto updateAll( ProductRequestDto productRequestDto,String correlationId) throws RuntimeException;
    public ProductResponseDto updatePartially( ProductRequestDto productRequestDto,String correlationId) throws RuntimeException;
    public void delete(String correlationId) throws RuntimeException;
    public ProductResponseDto getById( ProductRequestDto productRequestDto,String correlationId) throws RuntimeException;
    public ResponseEntity<List<ProductResponseDto>> getAll() throws RuntimeException;
    public List<ProductResponseDto> getByName(String name) throws RuntimeException;
}
