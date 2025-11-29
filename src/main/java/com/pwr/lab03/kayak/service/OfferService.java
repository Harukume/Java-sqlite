//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Offer;
//import com.pwr.lab03.kayak.dao.OfferRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OfferService {
//
//    private final OfferRepository repo;
//
//    public OfferService(OfferRepository repo){
//        this.repo = repo;
//    }
//
//    public Offer create(Offer Offer){
//        return repo.save(Offer);
//    }
//
//    public List<Offer> findAll(){
//        return repo.findAll();
//    }
//
//    public Offer findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Offer update(Offer Offer){
//        return repo.save(Offer);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Offer successfully deleted";
//    }
//}
