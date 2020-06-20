package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto mapToProductDto (Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup(),
                product.getCarts());
    }

    public Product mapToProduct (ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getGroup(),
                productDto.getCarts());
    }

    public List<ProductDto> mapToProductDtoList(List<Product> productList) {
        return productList.stream()
                .map(n -> new ProductDto(
                        n.getId(),
                        n.getName(),
                        n.getDescription(),
                        n.getPrice(),
                        n.getGroup(),
                        n.getCarts()))
                .collect(Collectors.toList());
    }


}
