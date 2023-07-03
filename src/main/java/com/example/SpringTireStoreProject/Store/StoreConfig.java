package com.example.SpringTireStoreProject.Store;

import com.example.SpringTireStoreProject.Wholesaler.WholesalerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StoreConfig {

    @Bean
    CommandLineRunner storeCommandLineRunner(StoreRepository repository) {
        return args -> {
            Store floydsTire = new Store(
                    1L,
                    "Floyds Tire",
                    "New Albany",
                    "Mississippi"
            );
            Store youngsTire = new Store(
                    "Youngs Tire",
                    "Pontotoc",
                    "Mississippi"
            );
            repository.saveAll(
                    List.of(floydsTire, youngsTire)
            );
        };
    }
}
