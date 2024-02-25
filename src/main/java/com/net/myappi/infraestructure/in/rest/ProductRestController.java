package com.net.myappi.infraestructure.in.rest;

import com.net.myappi.application.port.in.ProductPortIn;
import com.net.myappi.application.service.ProductsService;
import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/productsStore")
public class ProductRestController implements ProductPortIn {

    private final ProductsService productsService;

    public ProductRestController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @PostMapping("/product")
    @Override
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequestDto) throws RuntimeException {
        var response = Optional.of(productsService.create(productRequestDto)).orElseThrow(() -> new RuntimeException("ERROR - CREATE PRODUCT"));
        return ResponseEntity.ok(response);
    }


    @PutMapping@PostMapping("/product/{correlationId}")
    @Override
    public ProductResponseDto updateAll(@RequestBody ProductRequestDto productRequestDto, @PathVariable String correlationId) throws RuntimeException {
        return null;
    }

    @Override
    public ProductResponseDto updatePartially(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
    }


    @DeleteMapping("/products/{correlationId}")
    @Override
    public ResponseEntity<?> delete(@PathVariable String correlationId) throws RuntimeException {
        try {
            productsService.delete(correlationId);
            return ResponseEntity.ok().build();
        }catch (ResponseStatusException e){
            throw e;
        }

    }

    @GetMapping("/products/{correlationId}")
    @Override
    public ResponseEntity<ProductResponseDto> getById(@PathVariable  String correlationId) throws RuntimeException {
        var response = Optional.of(productsService.getById(correlationId)).orElseThrow(() -> new RuntimeException("ERROR - CREATE PRODUCT"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    @Override
    public ResponseEntity<List<ProductResponseDto>> getAll() throws RuntimeException {
        var response = Optional.of(productsService.getAll()).orElseThrow(() -> new RuntimeException("ERROR - GET ALL PRODUCT")).get();
        return ResponseEntity.ok(response);
    }

    @Override
    public List<ProductResponseDto> getByName(String name) throws RuntimeException {
        return null;
    }
}
