//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Task;
//import com.pwr.lab03.kayak.dao.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository repo;
//
//    public TaskService(TaskRepository repo){
//        this.repo = repo;
//    }
//
//    public Task create(Task Task){
//        return repo.save(Task);
//    }
//
//    public List<Task> findAll(){
//        return repo.findAll();
//    }
//
//    public Task findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Task update(Task Task){
//        return repo.save(Task);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Task successfully deleted";
//    }
//}
