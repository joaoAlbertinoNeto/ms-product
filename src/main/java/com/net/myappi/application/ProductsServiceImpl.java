package com.net.myappi.application;

import com.net.myappi.application.port.out.ProductPortOut;
import com.net.myappi.application.service.ProductsService;
import com.net.myappi.domain.dto.rest.ProductRequestDto;
import com.net.myappi.domain.dto.rest.ProductResponseDto;
import com.net.myappi.domain.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        //TODO melhorar
        String correlationId = UUID.randomUUID().toString();
        productRequestDto.setCorrelationId(correlationId);

        log.info("[SERVICE] - creating {} ... ",productRequestDto.getCorrelationId());
        try{
            var product = mapper.productRequestDtoToProduct(productRequestDto);
            productPortOut.create(product);
            log.info("[SERVICE] - created {} ... ",productRequestDto.getCorrelationId());
            return mapper.productToProductResponseDto(product);
        }catch (RuntimeException runtimeException){
            log.error("[ERROR] - error {}", runtimeException.getLocalizedMessage());
            throw new RuntimeException();
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

    }

    @Override
    public ProductResponseDto getById(ProductRequestDto productRequestDto, String correlationId) throws RuntimeException {
        return null;
    }

    @Override
    public Optional<List<ProductResponseDto>>  getAll() throws RuntimeException {
        try{
            log.info("[SERVICE] - get all data ... ");
            return Optional.of(productPortOut.getAll().stream().map(mapper::productToProductResponseDto).collect(Collectors.toList()));
        }catch (RuntimeException runtimeException){
            throw new RuntimeException();
        }
    }

    @Override
    public List<ProductResponseDto> getByName(String name) throws RuntimeException {
        return null;
    }
}
