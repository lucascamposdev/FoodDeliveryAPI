package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import presto.com.FoodDeliveryAPI.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String username);
}
