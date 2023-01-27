package com.naim.productservice.service;

import com.naim.productservice.Model.Product;
import com.naim.productservice.dto.ProductRequest;
import com.naim.productservice.dto.ProductResponse;
import com.naim.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

   private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} created successfully",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products=productRepository.findAll();

        return products.stream()
                .map(this::mapProductToProductResponse)
                .toList();

    }

    private ProductResponse mapProductToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
