package com.example.SpringTireStoreProject.Wholesaler;

import com.example.SpringTireStoreProject.Store.Store;
import com.example.SpringTireStoreProject.Wholesaler.Wholesaler;
import com.example.SpringTireStoreProject.Wholesaler.WholesalerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WholesalerService {
    private final WholesalerRepository wholesalerRepository;

    @Autowired
    public WholesalerService(WholesalerRepository wholesalerRepository) {
        this.wholesalerRepository = wholesalerRepository
        ;}

    public List<Wholesaler> getWholesalers() {return wholesalerRepository.findAll();}

    public Optional<Wholesaler> getwholesalerByName(String name) {
        Optional<Wholesaler> potentialWholesaler = wholesalerRepository.findWholesalerByName(name);
        if (!potentialWholesaler.isPresent()) {
            throw new IllegalStateException("There is not a wholesaler by that name!");
        } else {
            return potentialWholesaler;
        }
    }

    public void addNewWholesaler(Wholesaler wholesaler) {
        Optional<Wholesaler> potentialWholesaler = wholesalerRepository
                .findWholesalerByName(wholesaler.getCompany());
        if (potentialWholesaler.isPresent()) {
            throw new IllegalStateException("Wholesaler already exists!");
        }
        wholesalerRepository.save(wholesaler);
    }

    public void deleteWholesaler(String companyName) {
        Optional<Wholesaler> potentialWholesaler = wholesalerRepository.findWholesalerByName(companyName);
        if (potentialWholesaler.isPresent()) {
            wholesalerRepository.deleteById(potentialWholesaler.get().getId());
        } else {
            throw new IllegalStateException("No wholesaler by that name exists.");
        }
    }

    public void updateWholesaler(String companyName,
                                 String newName,
                                 String newWebsite) {
        Wholesaler wholesaler = wholesalerRepository.findWholesalerByName(companyName)
                .orElseThrow(() -> new IllegalStateException(
                        "Wholesaler with name " + companyName + " does not exist!"));

        if (newName != null &&
        newName.length() > 0 &&
        !Objects.equals(wholesaler.getCompany().toLowerCase(), newName.toLowerCase())) {
            Optional<Wholesaler> potentialWholesaler = wholesalerRepository.findWholesalerByName(companyName);
            if (potentialWholesaler.isPresent()) {
                throw new IllegalStateException(
                        "Wholesaler with name " + companyName + " does not exist!");
            }
            wholesaler.setCompany(newName);
        }

        if (newWebsite != null &&
                newWebsite.length() > 0 &&
                !Objects.equals(wholesaler.getCompany().toLowerCase(), newWebsite.toLowerCase())) {
            Optional<Wholesaler> potentialWholesaler = wholesalerRepository.findWholesalerByName(companyName);
            if (potentialWholesaler.isPresent()) {
                throw new IllegalStateException(
                        "Wholesaler with name " + companyName + " does not exist!");
            }
            wholesaler.setWebsite(newWebsite);
        }
    }

    public Wholesaler getOneWholesaler(Long id) {
        Wholesaler wholesaler = wholesalerRepository.getOne(id);
        return wholesaler;
    }

//    public Optional<Wholesaler> getOneWholesaler(Long id) {
//        Optional<Wholesaler> potentialWholesaler = wholesalerRepository.findById(id);
//        if (potentialWholesaler.isPresent()) {
//            return potentialWholesaler;
//        } else {
//            throw new IllegalStateException("There is no Wholesaler of id " + id + " in the system!");
//        }
//    }
}
