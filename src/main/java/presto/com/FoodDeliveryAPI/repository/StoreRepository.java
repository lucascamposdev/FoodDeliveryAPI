package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import presto.com.FoodDeliveryAPI.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
