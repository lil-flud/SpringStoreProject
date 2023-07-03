package com.example.SpringTireStoreProject.Wholesaler;

import jakarta.websocket.MessageHandler;
import com.example.SpringTireStoreProject.Wholesaler.Wholesaler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WholesalerRepository
        extends JpaRepository<Wholesaler, Long> {
        @Query("SELECT w FROM Wholesaler w WHERE w.company ILIKE ?1")
        Optional<Wholesaler> findWholesalerByName(String companyName);



}
