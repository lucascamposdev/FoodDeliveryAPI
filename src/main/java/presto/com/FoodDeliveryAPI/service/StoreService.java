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
import presto.com.FoodDeliveryAPI.dto.store.StoreResponseDto;
import presto.com.FoodDeliveryAPI.dto.store.StoreUpdateDto;
import presto.com.FoodDeliveryAPI.entity.*;
import presto.com.FoodDeliveryAPI.enums.AccountType;
import presto.com.FoodDeliveryAPI.repository.StoreRepository;
import presto.com.FoodDeliveryAPI.service.validations.openingDaysValidation;

import java.util.ArrayList;
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

        dto.getCredentials().setAccountType(AccountType.STORE);
        dto.getCredentials().setPassword(
                new BCryptPasswordEncoder().encode(dto.getCredentials().getPassword()));

        Store newStore = StoreMapper.toEntity(dto);
        return storeRepository.save(newStore);
    }

    public Store findById(Long id){
        return storeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma loja associada a este Id foi encontrada."));
    }

    public Page<Store> search(String query, Pageable page){
        return storeRepository.findByNameContainingIgnoreCase(query, page);
    }

    public Page<Store> findWhoDelivers(double latitude, double longitude, Pageable page){
        return storeRepository.findAllWhoDeliversAtThisLocation(latitude, longitude, page);
    }

    public Store update(Long id, StoreUpdateDto dto){
        Store store = storeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(("Nenhuma loja associada a este Id foi encontrada.")));

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

        List<OpeningHours> openingDaysCopy = new ArrayList<>();

        for (OpeningHours newOpeningHour : openingDaysDto) {
            OpeningHours openingHours = new OpeningHours(
                    null,
                    newOpeningHour.getDayOfWeek(),
                    newOpeningHour.getOpening(),
                    newOpeningHour.getClosing(),
                    store
            );

            openingDaysCopy.add(openingHours);
        }

        store.setOpeningDays(openingDaysCopy);
    }


}
