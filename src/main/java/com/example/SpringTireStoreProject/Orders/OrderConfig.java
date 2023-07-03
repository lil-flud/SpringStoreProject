package com.example.SpringTireStoreProject.Orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner orderCommandLineRunner(OrderRepository repository) {
        return args -> {
            Order nexensFromDK = new Order(
                    1L,
                    "Nexen Roadian HTX",
                    7,
                    LocalDate.of(2023, JUNE, 1)
            );
            Order summitsFromHarris = new Order(
                    "Summit Trail Climber AT",
                    8,
                    LocalDate.of(2023, JULY, 1)
            );
            repository.saveAll(
                    List.of(nexensFromDK, summitsFromHarris)
            );
        };
    }
}
