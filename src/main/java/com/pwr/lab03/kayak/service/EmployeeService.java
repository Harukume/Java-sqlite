//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Employee;
//import com.pwr.lab03.kayak.repository.EmployeeRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    private final EmployeeRepository repo;
//
//    public EmployeeService(EmployeeRepository repo){
//        this.repo = repo;
//    }
//
//    public Employee create(Employee Employee){
//        return repo.save(Employee);
//    }
//
//    public List<Employee> findAll(){
//        return repo.findAll();
//    }
//
//    public Employee findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Employee update(Employee Employee){
//        return repo.save(Employee);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Employee successfully deleted";
//    }
//}
