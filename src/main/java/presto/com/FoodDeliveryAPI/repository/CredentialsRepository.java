package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import presto.com.FoodDeliveryAPI.entity.Credentials;
import presto.com.FoodDeliveryAPI.entity.Store;
import presto.com.FoodDeliveryAPI.entity.User;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);


}
