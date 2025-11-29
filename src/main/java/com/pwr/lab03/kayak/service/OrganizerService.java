//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Organizer;
//import com.pwr.lab03.kayak.dao.OrganizerRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrganizerService {
//
//    private final OrganizerRepository repo;
//
//    public OrganizerService(OrganizerRepository repo){
//        this.repo = repo;
//    }
//
//    public Organizer create(Organizer Organizer){
//        return repo.save(Organizer);
//    }
//
//    public List<Organizer> findAll(){
//        return repo.findAll();
//    }
//
//    public Organizer findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Organizer update(Organizer Organizer){
//        return repo.save(Organizer);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Organizer successfully deleted";
//    }
//}
