package com.net.product.application;

import com.net.product.application.port.out.ProductPortOut;
import com.net.product.application.service.ProductsService;
import com.net.product.domain.dto.rest.ProductRequestDto;
import com.net.product.domain.dto.rest.ProductResponseDto;
import com.net.product.domain.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {

    private  ProductPortOut productPortOut;

    private ProductMapper mapper;

    public ProductsServiceImpl(ProductPortOut productPortOut) {
        this.productPortOut = productPortOut;
        this.mapper = ProductMapper.INSTANCE;
    }

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) throws RuntimeException {
        log.info("[SERVICE] - creating {} ... ",productRequestDto.getCorrelationId());
        var product = mapper.productRequestDtoToProduct(productRequestDto);
        product.setCorrelationId(createCorrelation());
        productPortOut.create(product);
        log.info("[SERVICE] - created {} ... ",productRequestDto.getCorrelationId());
        return mapper.productToProductResponseDto(product);
    }

    @Override
    public ProductResponseDto updateAll(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
    }

    @Override
    public ProductResponseDto updatePartially(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
    }

    @Override
    public void delete(String correlationId) throws RuntimeException {
        log.info("[SERVICE] - deleting {} ... ",correlationId);
        productPortOut.delete(correlationId);
    }

    @Override
    public ProductResponseDto getById(String correlationId) throws RuntimeException {
        log.info("[SERVICE] - geting {} ... ",correlationId);
        return mapper.productToProductResponseDto(productPortOut.getById(correlationId));

    }

    @Override
    public Optional<List<ProductResponseDto>>  getAll() throws RuntimeException {
        try{
            log.info("[SERVICE] - get all data ... ");
            return Optional.of(productPortOut.getAll().stream().map(mapper::productToProductResponseDto).collect(Collectors.toList()));
        }catch (RuntimeException runtimeException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OBJ_NOT_FOUND");
        }
    }

    @Override
    public Optional<List<ProductResponseDto>> getByName(String name) throws RuntimeException {
        log.info("[SERVICE] - geting {} ... ",name);
        var response = productPortOut.getByName(name);
        if(response.isEmpty()){
            throw new ObjectNotFoundException(Optional.ofNullable(null),"OBJ_NOT_FOUND");
        }

        return Optional.of(response.stream().map(mapper::productToProductResponseDto).collect(Collectors.toList()));

    }
    private static String createCorrelation(){
        return UUID.randomUUID().toString();
    }
}
