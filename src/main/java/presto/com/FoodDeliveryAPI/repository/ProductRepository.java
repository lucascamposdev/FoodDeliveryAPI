package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import presto.com.FoodDeliveryAPI.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
