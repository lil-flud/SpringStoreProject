package com.example.SpringTireStoreProject.Orders;

import com.example.SpringTireStoreProject.Store.Store;
import com.example.SpringTireStoreProject.Wholesaler.Wholesaler;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;

    private String tireName;

    private Integer amount;

    private LocalDate dateOrdered;

    public Order(Long id, String tireName, Integer amount, LocalDate dateOrdered) {
        this.id = id;
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = dateOrdered;
    }

    public Order(Long id, String tireName, Integer amount) {
        this.id = id;
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = LocalDate.now();
    }

    public Order(String tireName, Integer amount, LocalDate dateOrdered) {
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = dateOrdered;
    }

    public Order(String tireName, Integer amount) {
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = LocalDate.now();
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTireName() {
        return tireName;
    }

    public void setTireName(String tireName) {
        this.tireName = tireName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(LocalDate dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public void setDateOrdered() {
        this.dateOrdered = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tireName='" + tireName + '\'' +
                ", amount=" + amount +
                ", dateOrdered=" + dateOrdered +
                '}';
    }
}