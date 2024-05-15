package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import presto.com.FoodDeliveryAPI.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
