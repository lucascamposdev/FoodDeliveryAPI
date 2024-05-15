package presto.com.FoodDeliveryAPI.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.infra.exceptions.DataAlreadyExistsException;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.NotEqualDaysValidation;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final NotEqualDaysValidation notEqualDaysValidation;

    public StoreService(StoreRepository storeRepository, NotEqualDaysValidation notEqualDaysValidation) {
        this.storeRepository = storeRepository;
        this.notEqualDaysValidation = notEqualDaysValidation;
    }

    public Store register(StoreRequestDto dto){
        if(this.storeRepository.findByEmail(dto.getEmail()) != null){
            throw new DataAlreadyExistsException("email já está em uso.");
        }

        notEqualDaysValidation.validate(dto);
        dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        return storeRepository.save(StoreMapper.toEntity(dto));
    }

    public Page<Store> findAll(Pageable page){
        return storeRepository.findAll(page);
    }
}
