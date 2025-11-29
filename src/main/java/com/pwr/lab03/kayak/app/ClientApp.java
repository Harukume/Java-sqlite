package com.pwr.lab03.kayak.app;

import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.service.ClientService;

import java.util.List;
import java.util.Scanner;

public class ClientApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final ClientService service = new ClientService();


    public static void main(String[] args) {
        while(true) {
            System.out.println("1. View offers");
            System.out.println("2. Make reservation");
            System.out.println("3. View my reservations");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(sc.nextLine());


            try {
                switch(choice) {
                    case 1:
                        List<Offer> offers = service.viewOffers();
                        offers.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Client ID: ");
                        int clientId = Integer.parseInt(sc.nextLine());
                        System.out.print("Offer ID: ");
                        int offerId = Integer.parseInt(sc.nextLine());
                        System.out.print("Spots: ");
                        int spots = Integer.parseInt(sc.nextLine());
                        service.makeReservation(clientId, offerId, spots);
                        System.out.println("Reservation created.");
                        break;
                    case 3:
                        System.out.print("Client ID: ");
                        clientId = Integer.parseInt(sc.nextLine());
                        service.viewReservations(clientId).forEach(System.out::println);
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (DataException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}