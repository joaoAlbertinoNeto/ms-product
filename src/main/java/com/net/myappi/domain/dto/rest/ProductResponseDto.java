package com.net.myappi.domain.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductResponseDto {
    private String correlationId;
    private String name;
    private String description;
    private String correlationLink = "http://localhost:8080/productsStore/products";
    private Double price;
}