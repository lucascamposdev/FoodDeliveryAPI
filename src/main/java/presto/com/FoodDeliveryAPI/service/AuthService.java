package presto.com.FoodDeliveryAPI.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import presto.com.FoodDeliveryAPI.repository.CredentialsRepository;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    CredentialsRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public void deleteAccount(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Nenhuma conta associada a este Id foi encontrada.");
        }

        repository.deleteById(id);
    }
}
