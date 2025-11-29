package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Client;
import com.pwr.lab03.kayak.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service){
        this.service = service;
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return service.create(client);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Client update(Client client) {
        return service.update(client);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
