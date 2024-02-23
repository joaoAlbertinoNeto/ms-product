package com.net.myappi.infraestructure.in.rest;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.net.myappi.application.port.in.ProductPortIn;
import com.net.myappi.application.service.ProductsService;
import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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

    @Override
    public void delete(String correlationId) throws RuntimeException {

    }

    @Override
    public ProductResponseDto getById(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
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
