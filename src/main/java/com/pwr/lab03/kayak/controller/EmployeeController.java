package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Employee;
import com.pwr.lab03.kayak.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping
    public Employee create(@RequestBody Employee Employee) {
        return service.create(Employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Employee update(Employee Employee) {
        return service.update(Employee);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
