package com.pwr.lab03.kayak.controller;

import com.pwr.lab03.kayak.model.Task;
import com.pwr.lab03.kayak.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestBody Task Task) {
        return service.create(Task);
    }

    @GetMapping
    public List<Task> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Task getOne(Long id) {
        return service.findById(id);
    }

    @PutMapping
    public Task update(Task Task) {
        return service.update(Task);
    }

    @DeleteMapping("/{id}")
    public String delete(Long id) {
        return service.delete(id);
    }
}
