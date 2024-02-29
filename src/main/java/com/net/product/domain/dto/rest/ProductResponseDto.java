package com.net.product.domain.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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