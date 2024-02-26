package com.net.myappi.application.port.in;

import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.springframework.web.client.HttpServerErrorException.InternalServerError;

public interface ProductPortIn {
    public ResponseEntity<ProductResponseDto> create(ProductRequestDto productRequestDto);
    public ProductResponseDto updateAll(ProductRequestDto productRequestDto,String correlationId) ;
    public ProductResponseDto updatePartially(ProductRequestDto productRequestDto,String correlationId) ;
    public ResponseEntity<?> delete(String correlationId) ;
    public ResponseEntity<ProductResponseDto> getById(String correlationId) ;
    public ResponseEntity<List<ProductResponseDto>> getAll() ;
    public List<ProductResponseDto> getByName(String name) ;
}
