package presto.com.FoodDeliveryAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import presto.com.FoodDeliveryAPI.dto.store.*;
import presto.com.FoodDeliveryAPI.service.StoreService;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping
    public ResponseEntity<StoreResponseDto> register(@RequestBody @Valid StoreRequestDto dto){
        var createdStore = service.register(dto);
        var createdResponse = StoreMapper.toResponse(createdStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse);
    }

    @GetMapping
    public ResponseEntity<Page<StoreMinimalResponseDto>> findAll(Pageable page){
        var list = service.findAll(page);
        var listResponse = list.map(StoreMapper::toMinimalResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(listResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDto> update(@PathVariable Long id, @RequestBody StoreUpdateDto dto){
        var updatedStore = service.update(id, dto);
        var updatedResponse = StoreMapper.toResponse(updatedStore);
        return ResponseEntity.status(HttpStatus.OK).body(updatedResponse);
    }

}
