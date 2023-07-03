package com.example.SpringTireStoreProject.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository
        ;}

    public void addNewStore(Store store) {
        Optional<Store> potentialStore = storeRepository
                .findStoreByNameAndLocation(store.getStoreName(), store.getCity(), store.getState());
        if (potentialStore.isPresent()) {
            throw new IllegalStateException("Store already exists!");
        }
        storeRepository.save(store);
    }

    public Optional<Store> getStoreByNameAndLocation(String storeName, String city, String state) {
        Optional<Store> potentialStore = storeRepository.findStoreByNameAndLocation(storeName, city, state);
        if (potentialStore.isPresent()) {
            return potentialStore;
        } else { throw new IllegalStateException(
                    "There is no " + storeName + " in " + city + ", " +
                            state + " in the system!");
        }
    }

//    public Optional<Store> getOneStore(Long id) {
//        Optional<Store> potentialStore = storeRepository.findById(id);
//        if (potentialStore.isPresent()) {
//            return (Store) potentialStore;
//        } else {
//            throw new IllegalStateException("There is no store of id " + id + " in the system!");
//        }
//    }

    public Store getOneStore(Long id) {
        Store store = storeRepository.findById(id).get();
        return store;
    }

    public List<Store> getStores() { return storeRepository.findAll(); }

    public void deleteStore(String storeName, String city, String state) {
        Optional<Store> potentialStore = storeRepository.findStoreByNameAndLocation(storeName, city, state);
        if (potentialStore.isPresent()) {
            storeRepository.deleteById(potentialStore.get().getId());
        } else {
            throw new IllegalStateException("No Store of that name exists.");
        }
    }


    @Transactional
    public void updateStore(String storeName,
                            String city,
                            String state,
                            String newName,
                            String newCity,
                            String newState) {
        Store store = storeRepository.findStoreByNameAndLocation(storeName, city, state)
                .orElseThrow(() -> new IllegalStateException(
                        "Store with " + storeName + " does not exist."));
//        Store store = storeRepository.findStoreByName(storeName)
//                .orElseThrow(() -> new IllegalStateException(
//                        "Store with " + storeName + " does not exist."));

        if (newName != null &&
                newName.length() > 0 &&
        !Objects.equals(store.getStoreName(), newName)) {
            Optional<Store> potentialStore = storeRepository.findStoreByName(newName);
            if (potentialStore.isPresent()) {
                throw new IllegalStateException("A store with this name already exists!");
            }
            store.setStoreName(newName);
        }

        if (newCity != null &&
        newCity.length() > 0 &&
        !Objects.equals(store.getCity().toLowerCase(), newCity.toLowerCase())) {
            store.setCity(newCity);
        }

        if (newState != null &&
                newState.length() > 0 &&
                !Objects.equals(store.getState().toLowerCase(), newState.toLowerCase())) {
            store.setState(newState);
        }
    }

    public Store saveToDb(Store store) {
        return storeRepository.save(store);
    }
}
