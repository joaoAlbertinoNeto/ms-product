package com.net.myappi.infraestructure.in.rest;

import com.net.myappi.application.port.in.ProductPortIn;
import com.net.myappi.application.service.ProductsService;
import com.net.myappi.domain.dto.rest.ErrorResponseDto;
import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import com.net.myappi.infraestructure.in.config.Constants;
import com.net.myappi.infraestructure.in.exception.RestException;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/productsStore")
public class ProductRestController implements ProductPortIn {

    private final ProductsService productsService;

    public ProductRestController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct")
    @PostMapping("/product")
    @Override
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto productRequestDto)  {
        var response = Optional.of(productsService.create(productRequestDto)).orElseThrow(() -> new RuntimeException("ERROR - CREATE PRODUCT"));
        return ResponseEntity.ok(response);
    }

    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct")
    @PutMapping@PostMapping("/product/{correlationId}")
    @Override
    public ProductResponseDto updateAll(@RequestBody ProductRequestDto productRequestDto, @PathVariable String correlationId) throws RuntimeException {
        return null;
    }

    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct")
    @Override
    public ProductResponseDto updatePartially(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
    }

    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct")
    @DeleteMapping("/products/{correlationId}")
    @Override
    public ResponseEntity<?> delete(@PathVariable String correlationId){
        productsService.delete(correlationId);
        return ResponseEntity.ok().build();
    }


    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct" )
    @GetMapping("/products/{correlationId}")
    @Override
    public ResponseEntity<ProductResponseDto> getById(@PathVariable  String correlationId) throws RuntimeException {
        return ResponseEntity.ok(productsService.getById(correlationId));
    }




    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct" )
    @GetMapping("/products")
    @Override
    public ResponseEntity<List<ProductResponseDto>> getAll() throws RuntimeException {
        var response = Optional.of(productsService.getAll()).orElseThrow(() -> new RuntimeException("ERROR - GET ALL PRODUCT")).get();
        return ResponseEntity.ok(response);
    }
    @RateLimiter(name = "productratelimit" , fallbackMethod = "fallbackMethodProduct")
    @Override
    public List<ProductResponseDto> getByName(String name) throws RuntimeException {
        return null;
    }

    public ResponseEntity<?> fallbackMethodProduct(Throwable throwable){
        if(throwable instanceof ObjectNotFoundException){
            log.warn("[ADVICE] Error - Not Found");
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(),HttpStatus.NOT_FOUND.value(),Constants.NOT_FOUND_OBJECT_ERROR );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
        }
        log.warn("[ADVICE] Error - SERVICE UNAVAILABLE");
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(new Date(),HttpStatus.SERVICE_UNAVAILABLE.value(), Constants.SERVICE_NOT_AVAILABLE );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponseDto);
    }
}
