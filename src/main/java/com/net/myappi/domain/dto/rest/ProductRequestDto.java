package com.net.myappi.domain.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductRequestDto {
    private String correlationId;
    private String name;
    private String description;
    private Double price;
}