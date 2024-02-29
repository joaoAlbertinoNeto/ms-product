package com.net.product.infraestructure.in.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@ToString
@Setter
@Getter
public class RestException extends RuntimeException {
    private Date timestamp;
    private int status;
    private String message;

    public RestException(String message, Date timestamp, int status) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
