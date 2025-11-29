package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService service;

    public OfferController(OfferService service){
        this.service = service;
    }

    @PostMapping
    public Offer create(@RequestBody Offer Offer) {
        return service.create(Offer);
    }

    @GetMapping
    public List<Offer> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Offer getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Offer update(Offer Offer) {
        return service.update(Offer);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
