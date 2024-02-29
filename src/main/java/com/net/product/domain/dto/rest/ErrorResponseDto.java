package com.net.product.domain.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private Date timestamp;
    private int status;
    private String message;
}
