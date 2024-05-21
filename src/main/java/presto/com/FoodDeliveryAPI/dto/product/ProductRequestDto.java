package presto.com.FoodDeliveryAPI.dto.product;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import presto.com.FoodDeliveryAPI.entity.Store;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Nome do produto é obrigatório.")
    private String name;

    private String description;

    @NotNull(message = "Valor do produto é obrigatório.")
    private BigDecimal price;

    @NotNull(message = "Id da loja é obrigatório.")
    private Long storeId;
}
