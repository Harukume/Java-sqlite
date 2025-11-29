//package com.pwr.lab03.kayak.service;
//
//import com.pwr.lab03.kayak.model.Client;
//import com.pwr.lab03.kayak.dao.ClientRepository;
//
//import java.util.List;
//
//public class ClientService {
//
//    private final ClientRepository repo;
//
//    public ClientService(ClientRepository repo){
//        this.repo = repo;
//    }
//
//    public Client create(Client client){
//        return repo.save(client);
//    }
//
//    public List<Client> findAll(){
//        return repo.findAll();
//    }
//
//    public Client findById(Long id){
//        return repo.findById(id).orElse(null);
//    }
//
//    public Client update(Client client){
//        return repo.save(client);
//    }
//
//    public String delete(Long id){
//        repo.deleteById(id);
//        return "Client successfully deleted";
//    }
//}
