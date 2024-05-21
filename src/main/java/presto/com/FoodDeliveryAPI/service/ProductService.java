package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.product.ProductMapper;
import presto.com.FoodDeliveryAPI.dto.product.ProductRequestDto;
import presto.com.FoodDeliveryAPI.entity.Product;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.repository.ProductRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UtilityService utilityService;

    public Product register(ProductRequestDto dto){
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma loja associada a este Id foi encontrada."));

        utilityService.checkPermission(store.getCredentials().getId());

        Product newProduct = ProductMapper.toEntity(dto, store);
        return productRepository.save(newProduct);
    }
}
