package com.net.myappi.application;

import com.net.myappi.application.port.out.ProductPortOut;
import com.net.myappi.application.service.ProductsService;
import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import com.net.myappi.domain.mapper.ProductMapper;
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
        try{

            var product = mapper.productRequestDtoToProduct(productRequestDto);
            product.setCorrelationId(createCorrelation());
            productPortOut.create(product);
            log.info("[SERVICE] - created {} ... ",productRequestDto.getCorrelationId());
            return mapper.productToProductResponseDto(product);
        }catch (RuntimeException runtimeException){
            log.error("[ERROR] - error {}", runtimeException.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"ERROR_CREATE");
        }
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
        try{
            productPortOut.delete(correlationId);
        }catch (ObjectNotFoundException runtimeException){
            log.error("[ERROR] - error {}", runtimeException.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OBJ_NOT_FOUND");
        }
    }

    @Override
    public ProductResponseDto getById(String correlationId) throws RuntimeException {
        log.info("[SERVICE] - geting {} ... ",correlationId);
        try{
            return mapper.productToProductResponseDto(productPortOut.getById(correlationId).orElseThrow(() -> new RuntimeException()));
        }catch (ObjectNotFoundException runtimeException){
            log.error("[ERROR] - error {}", runtimeException.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OBJ_NOT_FOUND");
        }
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
    public List<ProductResponseDto> getByName(String name) throws RuntimeException {
        return null;
    }


    private static String createCorrelation(){
        return UUID.randomUUID().toString();
    }
}
