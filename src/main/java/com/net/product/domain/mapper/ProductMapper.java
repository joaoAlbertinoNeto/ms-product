package com.net.product.domain.mapper;

import com.net.product.domain.db.Product;
import com.net.product.domain.dto.rest.ProductRequestDto;
import com.net.product.domain.dto.rest.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    @Mapping(target = "id" ,ignore = true)
    public Product productRequestDtoToProduct(ProductRequestDto productRequestDto);

    @Mapping(target = "correlationLink" ,ignore = true)
    public ProductResponseDto productToProductResponseDto(Product productRequestDto);
}
