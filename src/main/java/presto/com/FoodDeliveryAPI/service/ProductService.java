package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.product.ProductMapper;
import presto.com.FoodDeliveryAPI.dto.product.ProductRequestDto;
import presto.com.FoodDeliveryAPI.dto.product.ProductUpdateDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Product;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.ProductRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;

import java.util.Objects;

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


        public Product update(ProductUpdateDto dto){
            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Nenhum produto associado a este Id foi encontrado."));

            utilityService.checkPermission(product.getStore().getCredentials().getId());

        if(dto.getName() != null){
            product.setName(dto.getName());
        }
        if(dto.getDescription() != null){
            product.setDescription(dto.getDescription());
        }
        if(dto.getValue() != null){
            product.setValue(dto.getValue());
        }

        return productRepository.save(product);
    }

    public void delete (Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum produto associado a este Id foi encontrado."));

        utilityService.checkPermission(product.getStore().getCredentials().getId());

        productRepository.deleteById(id);
    }
}
