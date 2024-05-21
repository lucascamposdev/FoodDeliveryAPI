package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presto.com.FoodDeliveryAPI.dto.product.ProductMapper;
import presto.com.FoodDeliveryAPI.dto.product.ProductRequestDto;
import presto.com.FoodDeliveryAPI.dto.product.ProductResponseDto;
import presto.com.FoodDeliveryAPI.dto.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dto.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dto.store.StoreResponseDto;
import presto.com.FoodDeliveryAPI.service.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> register(@RequestBody @Valid ProductRequestDto dto){
        var createdProduct = productService.register(dto);
        var createdResponse = ProductMapper.toResponse(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse);
    }

}
