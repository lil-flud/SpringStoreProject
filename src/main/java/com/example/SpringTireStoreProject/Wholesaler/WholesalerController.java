package com.example.SpringTireStoreProject.Wholesaler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/wholesalers")
public class WholesalerController {

    private final WholesalerService wholesalerService;

    @Autowired
    public WholesalerController(WholesalerService wholesalerService) {
        this.wholesalerService = wholesalerService;
    }

    @GetMapping
    public List<Wholesaler> getWholesalers() {return wholesalerService.getWholesalers();}

    @GetMapping(path = "{companyName}")
    public Optional<Wholesaler> getwholesalerByName(@PathVariable("companyName") String name) {
        System.out.println(wholesalerService.getwholesalerByName(name));
        return wholesalerService.getwholesalerByName(name);
    }

    @PostMapping
    public void registerNewWholesaler(@RequestBody Wholesaler wholesaler) {
        wholesalerService.addNewWholesaler(wholesaler);
    }

    @DeleteMapping(path = "delwholesaler/{companyName}")
    public void deleteWholesaler(@PathVariable("companyName") String name) {
        wholesalerService.deleteWholesaler(name);
    }

    @PutMapping(path = "updatewholesaler/{companyName}")
    public void updateWholesaler(
            @PathVariable("companyName") String companyName,
            @RequestParam(required = false) String newName,
            @RequestParam(required = false) String newWebsite) {
        wholesalerService.updateWholesaler(companyName, newName, newWebsite);
    }
}

