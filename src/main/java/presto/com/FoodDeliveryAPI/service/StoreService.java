package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.dto.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dto.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dto.store.StoreUpdateDto;
import presto.com.FoodDeliveryAPI.entity.*;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.openingDaysValidation;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private openingDaysValidation openingDaysValidation;

    public Store register(StoreRequestDto dto){

        openingDaysValidation.validate(dto.getOpeningDays());

        Credentials registerCredentials = dto.getCredentials();

        registerCredentials.setAccountType(AccountType.STORE);
        registerCredentials.setPassword(
                new BCryptPasswordEncoder().encode(registerCredentials.getPassword()));

        dto.setCredentials(registerCredentials);

        Store newStore = StoreMapper.toEntity(dto);
        return storeRepository.save(newStore);
    }

    public Page<Store> findAll(Pageable page){
        return storeRepository.findAll(page);
    }

    public Page<Store> findWhoDelivers(double latitude, double longitude, Pageable page){
        List<Store> list = storeRepository.findAllWhoDeliversAtThisLocation(latitude, longitude);

        int start = (int) page.getOffset();
        int end = Math.min((start + page.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), page, list.size());
    }

    public Store update(Long id, StoreUpdateDto dto){
        Store store = storeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Nenhuma conta associada a este Id foi encontrada.")));

        utilityService.checkPermission(store.getCredentials().getId());

        if (dto.getName() != null) {
            store.setName(dto.getName());
        }
        if (dto.getDeliveryRadius() != null) {
            store.setDeliveryRadius(dto.getDeliveryRadius());
        }
        if (dto.getLocation() != null) {
            store.setLocation(dto.getLocation());
        }
        if (dto.getCredentials() != null) {
            utilityService.updateCredentials(store.getCredentials(), dto.getCredentials());
        }
        if (dto.getAddress() != null) {
            utilityService.updateAddress(store.getAddress(), dto.getAddress());
        }
        if (dto.getOpeningDays() != null) {
            updateOpeningHours(store, dto.getOpeningDays());
        }

        return storeRepository.save(store);
    }

    private void updateOpeningHours(Store store, List<OpeningHours> openingDaysDto) {
        openingDaysValidation.validate(openingDaysDto);

        store.getOpeningDays().clear();
        for (OpeningHours newOpeningHour : openingDaysDto) {
            OpeningHours openingHours = new OpeningHours(
                    null,
                    newOpeningHour.getDayOfWeek(),
                    newOpeningHour.getOpening(),
                    newOpeningHour.getClosing(),
                    store
            );

            store.getOpeningDays().add(openingHours);
        }
    }
}
