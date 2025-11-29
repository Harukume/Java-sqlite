package com.pwr.lab03.kayak.app;

import com.pwr.lab03.kayak.service.OrganizerService;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


class OrganizerApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final OrganizerService service = new OrganizerService();


    public static void main(String[] args) {
        while(true) {
            System.out.println("1. View offers");
            System.out.println("2. Create offer");
            System.out.println("3. Confirm reservation");
            System.out.println("4. Generate task");
            System.out.println("0. Exit");
            int choice = Integer.parseInt(sc.nextLine());


            try {
                switch(choice) {
                    case 1:
                        List<Offer> offers = service.viewOffers();
                        offers.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Date (YYYY-MM-DD): ");
                        String sqlDate =  sc.nextLine();
                        System.out.print("Location: ");
                        String location = sc.nextLine();
                        System.out.print("Max spots: ");
                        int maxSpots = Integer.parseInt(sc.nextLine());
                        Offer offer = new Offer(0, sqlDate, location, maxSpots, maxSpots);
                        service.createOffer(offer);
                        System.out.println("Offer created.");
                        break;
                    case 3:
                        System.out.print("Reservation ID: ");
                        int resId = Integer.parseInt(sc.nextLine());
                        service.confirmReservation(resId);
                        System.out.println("Reservation confirmed.");
                        break;
                    case 4:
                        System.out.print("Organizer ID: ");
                        int orgId = Integer.parseInt(sc.nextLine());
                        System.out.print("Employee ID: ");
                        int empId = Integer.parseInt(sc.nextLine());
                        System.out.print("Offer ID: ");
                        int offerId = Integer.parseInt(sc.nextLine());
                        service.generateTask(orgId, empId, offerId);
                        System.out.println("Task generated.");
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