package presto.com.FoodDeliveryAPI.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Nome do produto é obrigatório.")
    private String name;

    private String description;

    @NotNull(message = "Valor do produto é obrigatório.")
    private BigDecimal value;
}
