package com.example.SpringTireStoreProject.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s FROM Store s WHERE s.storeName ILIKE ?1")
    Optional<Store> findStoreByName(String name);

    @Query("SELECT s FROM Store s WHERE s.storeName ILIKE ?1 AND s.city ILIKE ?2 and s.state ILIKE ?3")
    Optional<Store> findStoreByNameAndLocation(String name, String city, String state);
}
