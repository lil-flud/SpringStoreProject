package com.example.SpringTireStoreProject.Store;

//import com.example.SpringTireStoreProject.Orders.OrderService;
import com.example.SpringTireStoreProject.Wholesaler.Wholesaler;
import com.example.SpringTireStoreProject.Wholesaler.WholesalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    private final WholesalerService wholesalerService;

//    private final OrderService orderService;

    @Autowired
    public StoreController(StoreService storeService, WholesalerService wholesalerService) {
        this.storeService = storeService;
        this.wholesalerService = wholesalerService;
//        this.orderService = orderService;
    }



//    @Autowired
//    StoreRepository storeRepository;

    @GetMapping
    public List<Store> getStores() { return storeService.getStores(); }

    @GetMapping(path = "{storeName}/{city}/{state}")
    public Optional<Store> getStore(@PathVariable("storeName") String name,
                                    @PathVariable("city") String city,
                                    @PathVariable("state") String state) {
        return storeService.getStoreByNameAndLocation(name, city, state);
    }

    @PostMapping
    public void registerNewStore(@RequestBody Store store) {
        storeService.addNewStore(store);
    ;}

    @DeleteMapping(path = "delstore/{storeName}/{city}/{state}")
    public void deleteStore(@PathVariable("storeName") String name,
                            @PathVariable("city") String city,
                            @PathVariable("state") String state) {
        storeService.deleteStore(name, city, state);
    }

    @PutMapping(path = "updatestore/{storeName}/{city}/{state}")
    public void updateStore(@PathVariable("storeName") String storeName,
                            @PathVariable("city") String city,
                            @PathVariable("state") String state,
                            @RequestParam(required = false) String newName,
                            @RequestParam(required = false) String newCity,
                            @RequestParam(required = false) String newState) {
        storeService.updateStore(storeName, city, state, newName, newCity, newState);
    }

    @PutMapping("/{storeId}/wholesalers/{wholesalerId}")
    public Store addWholeSalerToCollection(
            @PathVariable("storeId") Long storeId,
            @PathVariable("wholesalerId") Long wholesalerId) {
        Store store = storeService.getOneStore(storeId);
        Wholesaler wholesaler = wholesalerService.getOneWholesaler(wholesalerId);
        store.collectWholesaler(wholesaler);
        return storeService.saveToDb(store);
    }

}
