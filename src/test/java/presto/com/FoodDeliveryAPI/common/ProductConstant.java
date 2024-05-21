package presto.com.FoodDeliveryAPI.common;

import presto.com.FoodDeliveryAPI.dto.product.ProductRequestDto;
import presto.com.FoodDeliveryAPI.dto.product.ProductUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Product;

import java.math.BigDecimal;

import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;

public class ProductConstant {

    public static final Product PRODUCT = new Product(
            1L,
            "Product",
            "A description",
            new BigDecimal("9.99"),
            STORE
    );
    public static final ProductRequestDto PRODUCT_REQUEST_DTO = new ProductRequestDto(
            "Product",
            "A description",
            new BigDecimal("9.99"),
            STORE.getId()
    );

    public static final ProductRequestDto INVALID_PRODUCT_REQUEST_DTO = new ProductRequestDto(
            "Invalid",
            null,
            null,
            null
    );

    public static final ProductUpdateDto PRODUCT_UPDATE_DTO = new ProductUpdateDto(
            "New Product",
            "New description",
            new BigDecimal("19.99"),
            STORE.getId()
    );
}
