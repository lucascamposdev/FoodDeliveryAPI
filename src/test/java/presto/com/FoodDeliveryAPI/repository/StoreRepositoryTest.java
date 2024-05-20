package presto.com.FoodDeliveryAPI.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import presto.com.FoodDeliveryAPI.entity.Location;
import presto.com.FoodDeliveryAPI.entity.Store;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static presto.com.FoodDeliveryAPI.common.AddressConstant.ADDRESS;
import static presto.com.FoodDeliveryAPI.common.LocationConstant.LOCATION;
import static presto.com.FoodDeliveryAPI.common.StoreConstant.STORE;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void findAllWhoDelivers_WithCloseLocation_ReturnsStore() {
        Store store = new Store();
        store.setName("Store");
        store.setLocation(LOCATION);
        store.setAddress(ADDRESS);
        store.setDeliveryRadius(10);

        testEntityManager.persist(store);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Store> result = storeRepository.findAllWhoDeliversAtThisLocation(-123.456, 456.789, pageable);

        assertThat(result.getContent()).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("Store");
    }

    @Test
    void findAllWhoDelivers_WithFarLocation_ReturnsNothing() {
        Store store = new Store();
        store.setName("Store");
        store.setLocation(new Location(789.101,-123.456));
        store.setAddress(ADDRESS);
        store.setDeliveryRadius(10);

        testEntityManager.persist(store);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Store> result = storeRepository.findAllWhoDeliversAtThisLocation(-456.789, 123.456, pageable);

        assertThat(result.getContent()).isEmpty();
    }

    @Test
    void findAllWhoDelivers_WithCloseLocationButSmallDeliveryArea_ReturnsNothing() {
        Store store = new Store();
        store.setName("Store");
        store.setLocation(new Location(-456.789, 123.456));
        store.setAddress(ADDRESS);
        store.setDeliveryRadius(0);

        testEntityManager.persist(store);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Store> result = storeRepository.findAllWhoDeliversAtThisLocation(-456.789, 123.456, pageable);

        assertThat(result.getContent()).isEmpty();
    }

}