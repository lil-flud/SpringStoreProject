package com.example.SpringTireStoreProject.Wholesaler;


import com.example.SpringTireStoreProject.Store.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
//import org.springframework.data.annotation.Id;

@Entity
@Table
public class Wholesaler {
    @Id
    @SequenceGenerator(
            name = "wholesaler_sequence",
            sequenceName = "wholesaler_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wholesaler_sequence"
    )
    private Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "wholesalerCollection")
    private Set<Store> wholesalers_stores = new HashSet<>();
    private String company;
    private String website;

    public Set<Store> getWholesalers_stores() {
        return wholesalers_stores;
    }

    public Wholesaler() {

    }

    public Wholesaler(Long id, String company, String website) {
        this.id = id;
        this.company = company;
        this.website = website;
    }

    public Wholesaler(String company, String website) {
        this.company = company;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Wholesaler{" +
                "id=" + id +
                ", wholesalers_stores=" + wholesalers_stores +
                ", company='" + company + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
