package presto.com.FoodDeliveryAPI.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank(message = "Nome da rua é obrigatório.")
    private String address_street;
    @NotBlank(message = "Numero do endereço é obrigatório.")
    private String address_number;
    @NotBlank(message = "Bairro é obrigatório.")
    private String address_neighborhood;
    @NotBlank(message = "Cidade é obrigatória.")
    private String address_city;
    @NotBlank(message = "Estado é obrigatório.")
    private String address_state;
    @NotBlank(message = "CEP é obrigatório.")
    private String address_cep;
    private String address_complement;
}
