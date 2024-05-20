package presto.com.FoodDeliveryAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.entity.Address;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.infra.exceptions.InvalidPermissionException;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;

import java.util.Objects;

@Service
public class UtilityService {
    public void updateCredentials(Credentials credentials, Credentials credentialsDto){
        if (credentialsDto.getEmail() != null) {
            credentials.setEmail(credentialsDto.getEmail());
        }
        if (credentialsDto.getPassword() != null) {
            credentials.setEmail(credentialsDto.getPassword());
        }
    }

    public void updateAddress(Address address, Address addressDto) {
        if (addressDto.getAddress_street() != null) {
            address.setAddress_street(addressDto.getAddress_street());
        }
        if (addressDto.getAddress_number() != null) {
            address.setAddress_number(addressDto.getAddress_number());
        }
        if (addressDto.getAddress_complement() != null) {
            address.setAddress_complement(addressDto.getAddress_complement());
        }
        if (addressDto.getAddress_neighborhood() != null) {
            address.setAddress_neighborhood(addressDto.getAddress_neighborhood());
        }
        if (addressDto.getAddress_city() != null) {
            address.setAddress_city(addressDto.getAddress_city());
        }
        if (addressDto.getAddress_state() != null) {
            address.setAddress_state(addressDto.getAddress_state());
        }
        if (addressDto.getAddress_cep() != null) {
            address.setAddress_cep(addressDto.getAddress_cep());
        }
    }

    public void checkPermission(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long currentAuthenticatedId = ((Credentials) authentication.getPrincipal()).getId();

        if (!Objects.equals(id, currentAuthenticatedId)) {
            throw new InvalidPermissionException("Você não tem permissão para acessar este perfil.");
        }
    }
}
