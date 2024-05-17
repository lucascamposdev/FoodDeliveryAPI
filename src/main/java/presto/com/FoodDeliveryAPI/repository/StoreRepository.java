package presto.com.FoodDeliveryAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import presto.com.FoodDeliveryAPI.entity.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByCredentialsId(Long credentialsId);
    Page<Store> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT s FROM Store s WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(s.location.latitude)) * " +
            "cos(radians(s.location.longitude) - radians(:longitude)) + sin(radians(:latitude)) * " +
            "sin(radians(s.location.latitude))) <= s.deliveryRadius")
    List<Store> findAllWhoDeliversAtThisLocation(@Param("latitude") double latitude,
                                                 @Param("longitude") double longitude);
}
