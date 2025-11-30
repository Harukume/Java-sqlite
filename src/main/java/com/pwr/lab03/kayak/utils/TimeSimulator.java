package com.pwr.lab03.kayak.utils;

import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.service.OfferService;
import com.pwr.lab03.kayak.service.ReservationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeSimulator {

    private static TimeSimulator instance;

    private LocalDate simulatedDate;
    private final OfferService offerService = new OfferService();
    private final ReservationService reservationService = new ReservationService();

    private final List<TimeListener> listeners = new ArrayList<>();

    private TimeSimulator(LocalDate startDate) {
        this.simulatedDate = startDate;
    }

    public static TimeSimulator getInstance() {
        if (instance == null) {
            instance = new TimeSimulator(LocalDate.now());
        }
        return instance;
    }

    public LocalDate getSimulatedDate() {
        return simulatedDate;
    }

    public void advanceDays(int days) {
        simulatedDate = simulatedDate.plusDays(days);

        updateReservationsStatus();

        for (TimeListener listener : listeners) {
            listener.onDateChanged(simulatedDate);
        }
    }

    public void addListener(TimeListener listener) {
        listeners.add(listener);
    }

    private void updateReservationsStatus() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();

            List<Integer> toConfirm = new ArrayList<>();
            List<Integer> toStart = new ArrayList<>();
            List<Integer> toComplete = new ArrayList<>();

            for (Reservation r : reservations) {
                Offer offer = offerService.getOfferById(r.getOfferId());
                LocalDate offerDate = LocalDate.parse(offer.getDate());

                switch (r.getStatus()) {
                    case "created":
                        if (!simulatedDate.isBefore(offerDate.minusDays(1)))
                            toConfirm.add(r.getId());
                        break;
                    case "confirmed":
                        if (!simulatedDate.isBefore(offerDate))
                            toStart.add(r.getId());
                        break;
                    case "in_progress":
                        if (!simulatedDate.isBefore(offerDate.plusDays(1)))
                            toComplete.add(r.getId());
                        break;
                }
            }

            for (int id : toConfirm) reservationService.confirmReservation(id);
            for (int id : toStart) reservationService.startReservation(id);
            for (int id : toComplete) reservationService.completeReservation(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
