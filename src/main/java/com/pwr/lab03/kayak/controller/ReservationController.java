package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service){
        this.service = service;
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation Reservation) {
        return service.create(Reservation);
    }

    @GetMapping
    public List<Reservation> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Reservation update(Reservation Reservation) {
        return service.update(Reservation);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
