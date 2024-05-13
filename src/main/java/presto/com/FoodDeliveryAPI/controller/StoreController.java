package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMapper;
import presto.com.FoodDeliveryAPI.dtos.store.StoreMinimalResponseDto;
import presto.com.FoodDeliveryAPI.dtos.store.StoreRequestDto;
import presto.com.FoodDeliveryAPI.dtos.store.StoreResponseDto;
import presto.com.FoodDeliveryAPI.service.StoreService;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreResponseDto> register(@RequestBody @Valid StoreRequestDto dto){
        var createdStore = storeService.register(dto);
        var createdStoreResponse = StoreMapper.toResponse(createdStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStoreResponse);
    }

    @GetMapping
    public ResponseEntity<Page<StoreMinimalResponseDto>> findAll(Pageable page){
        var list = storeService.findAll(page);
        var listResponse = list.map(StoreMapper::toMinimalResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(listResponse);
    }
}
