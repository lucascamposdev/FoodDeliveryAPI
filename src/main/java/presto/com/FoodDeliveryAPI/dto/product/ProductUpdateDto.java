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
public class ProductUpdateDto {

    private String name;

    private String description;

    private BigDecimal value;

    @NotNull(message = "Id do produto é obrigatório.")
    private Long productId;
}
