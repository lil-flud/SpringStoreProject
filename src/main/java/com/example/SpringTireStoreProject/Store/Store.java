package com.example.SpringTireStoreProject.Store;

import com.example.SpringTireStoreProject.Orders.Orders;
import com.example.SpringTireStoreProject.Wholesaler.Wholesaler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Store {

    @Id
    @SequenceGenerator(
            name = "store_sequence",
            sequenceName = "store_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "store_sequence"
    )
    private Long id;

    @ManyToMany
    @JoinTable(
            name="wholesaler_collected",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name= "wholesaler_id")
    )
    private Set<Wholesaler> wholesalerCollection = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "store")
    private Set<Orders> orders = new HashSet<>();
    private String storeName;
    private String city;
    private String state;

    public Store(Long id, String storeName, String city, String state) {
        this.id = id;
        this.storeName = storeName;
        this.city = city;
        this.state = state;
    }

    public Store(String storeName, String city, String state) {
        this.storeName = storeName;
        this.city = city;
        this.state = state;
    }

    public Store() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Wholesaler> getWholesalerCollection() {
        return wholesalerCollection;
    }

    public void setWholesalerCollection(Set<Wholesaler> wholesalerCollection) {
        this.wholesalerCollection = wholesalerCollection;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", wholesalerCollection=" + wholesalerCollection +
                ", orders=" + orders +
                ", storeName='" + storeName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public void collectWholesaler(Wholesaler wholesaler) {
        wholesalerCollection.add(wholesaler);
    }

    public void putInAnOrder(Orders order) { orders.add(order); }

}
