package presto.com.FoodDeliveryAPI.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store register(StoreRequestDto dto){
        return storeRepository.save(StoreMapper.toEntity(dto));
    }

    public Page<Store> findAll(Pageable page){
        return storeRepository.findAll(page);
    }
}
