package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dtos.store.StoreResponseDto;
import presto.com.FoodDeliveryAPI.service.StoreService;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreResponseDto> register(@RequestBody @Valid StoreRequestDto dto){
        var createdStore = StoreMapper.returnStoreResponseFromStoreEntity(storeService.register(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStore);
    }
}
