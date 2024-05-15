package presto.com.FoodDeliveryAPI.dto.credentials;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRequestDto {

    @NotBlank(message = "Email não deve ser nulo.")
    @Email(message = "Insira um email válido.")
    private String email;

    @NotBlank(message = "Senha não deve ser nulo.")
    private String password;
}
