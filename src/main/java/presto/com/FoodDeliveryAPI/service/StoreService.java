package presto.com.FoodDeliveryAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.infra.exceptions.DataAlreadyExistsException;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.NotEqualDaysValidation;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private NotEqualDaysValidation notEqualDaysValidation;

    public Store register(StoreRequestDto dto){
        if(credentialsRepository.existsByEmail(dto.getCredentials().getEmail())){
            throw new DataAlreadyExistsException("email já está em uso.");
        }

        notEqualDaysValidation.validate(dto);

        Credentials registerCredentials = dto.getCredentials();

        registerCredentials.setPassword(
                new BCryptPasswordEncoder().encode(registerCredentials.getPassword()));

        dto.setCredentials(registerCredentials);

        Store newStore = StoreMapper.toEntity(dto);
        return storeRepository.save(newStore);
    }

    public Page<Store> findAll(Pageable page){
        return storeRepository.findAll(page);
    }
}
