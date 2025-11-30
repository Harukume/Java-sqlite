package com.pwr.lab03.kayak.gui;

import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.service.ClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientReservationsWindow extends JFrame {

    private final ClientService clientService = new ClientService();
    private JTable table;

    public ClientReservationsWindow(int clientId) {
        setTitle("Your Reservations");
        setSize(600, 400);
        setLocationRelativeTo(null);

        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadReservations(clientId);
    }

    private void loadReservations(int clientId) {
        try {
            List<Reservation> list = clientService.viewReservations(clientId);

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Client ID", "Offer ID", "Organizer ID", "Number of seats", "Status"},
                    0
            );

            for (Reservation r : list) {
                model.addRow(new Object[]{
                        r.getId(),
                        r.getClientId(),
                        r.getOfferId(),
                        r.getOrganizerId(),
                        r.getNumberOfSeats(),
                        r.getStatus()
                });
            }

            table.setModel(model);

        } catch (DataException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading reservations: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}