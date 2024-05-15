package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import presto.com.FoodDeliveryAPI.entity.Credentials;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);
}
