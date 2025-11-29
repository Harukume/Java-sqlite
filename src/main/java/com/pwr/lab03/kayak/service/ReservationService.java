//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Reservation;
//import com.pwr.lab03.kayak.repository.ReservationRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ReservationService {
//
//    private final ReservationRepository repo;
//
//    public ReservationService(ReservationRepository repo){
//        this.repo = repo;
//    }
//
//    public Reservation create(Reservation Reservation){
//        return repo.save(Reservation);
//    }
//
//    public List<Reservation> findAll(){
//        return repo.findAll();
//    }
//
//    public Reservation findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Reservation update(Reservation Reservation){
//        return repo.save(Reservation);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Reservation successfully deleted";
//    }
//}
