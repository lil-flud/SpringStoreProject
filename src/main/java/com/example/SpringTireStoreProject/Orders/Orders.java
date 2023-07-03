package com.example.SpringTireStoreProject.Orders;

import com.example.SpringTireStoreProject.Store.Store;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Orders {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    private String tireName;

    private Integer amount;

    private LocalDate dateOrdered;

    public Orders(Long id, String tireName, Integer amount, LocalDate dateOrdered) {
        this.id = id;
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = dateOrdered;
    }

    public Orders(Long id, String tireName, Integer amount) {
        this.id = id;
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = LocalDate.now();
    }

    public Orders(String tireName, Integer amount, LocalDate dateOrdered) {
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = dateOrdered;
    }

    public Orders(String tireName, Integer amount) {
        this.tireName = tireName;
        this.amount = amount;
        this.dateOrdered = LocalDate.now();
    }

    public Orders() {
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", tireName='" + tireName + '\'' +
                ", amount=" + amount +
                ", dateOrdered=" + dateOrdered +
                '}';
    }
}