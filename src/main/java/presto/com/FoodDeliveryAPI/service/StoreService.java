package presto.com.FoodDeliveryAPI.service;

import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store register(StoreRequestDto dto){
        return storeRepository.save(StoreMapper.returnStoreFromStoreRequestDto(dto));
    }
}
