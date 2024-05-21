package presto.com.FoodDeliveryAPI.dto.product;

import presto.com.FoodDeliveryAPI.entity.OpeningHours;
import presto.com.FoodDeliveryAPI.entity.Product;
import presto.com.FoodDeliveryAPI.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductResponseDto toResponse(Product entity){
        return new ProductResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
    public static List<Product> toList(Store store){
        List<Product> list = new ArrayList<>();

        for(Product product : store.getProducts()){
            list.add(new Product(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    store));
        }
        return list;
    }

    public static Product toEntity(ProductRequestDto dto, Store store){
        return new Product(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
               store
        );
    }
}
