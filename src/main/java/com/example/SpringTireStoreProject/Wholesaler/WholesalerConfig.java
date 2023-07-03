package com.example.SpringTireStoreProject.Wholesaler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WholesalerConfig {

    @Bean
    CommandLineRunner commandLineRunner(WholesalerRepository repository) {
        return args -> {
            Wholesaler dunlapKyle = new Wholesaler(
                    1L,
                    "Dunlap and Kyle",
                    "d&k@tireweb.com"
            );
            Wholesaler friendTire = new Wholesaler(
                    "Friend Tire",
                    "friendtire.com"
            );
            repository.saveAll(
                    List.of(dunlapKyle, friendTire)
            );
        };
    }
}
