package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Organizer;
import com.pwr.lab03.kayak.service.OrganizerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/organizers")
public class OrganizerController {
    private final OrganizerService service;

    public OrganizerController(OrganizerService service){
        this.service = service;
    }

    @PostMapping
    public Organizer create(@RequestBody Organizer Organizer) {
        return service.create(Organizer);
    }

    @GetMapping
    public List<Organizer> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Organizer getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Organizer update(Organizer Organizer) {
        return service.update(Organizer);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
