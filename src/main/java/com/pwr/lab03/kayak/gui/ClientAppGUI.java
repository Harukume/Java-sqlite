package com.pwr.lab03.kayak.gui;

import com.pwr.lab03.kayak.exception.DataException;
import com.pwr.lab03.kayak.exception.ValidationException;
import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.service.ClientService;
import com.pwr.lab03.kayak.service.OfferService;
import com.pwr.lab03.kayak.utils.TimeSimulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClientAppGUI extends JFrame {

    private final OfferService offerService;
    private final ClientService clientService;

    private JTable table;
    private JTextField clientIdField;
    private JTextField offerIdField;
    private JTextField spotsField;
    private JTextArea messageArea;
    private JLabel dateLabel;

    public ClientAppGUI() {
        this.offerService = new OfferService();
        this.clientService = new ClientService();

        setTitle("Client Application");
        setSize(630, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(900,400);

        setLayout(new BorderLayout());

        // --- Tabela ofert --- //
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh offers");
        refreshBtn.addActionListener(e -> loadOffers());
        add(refreshBtn, BorderLayout.NORTH);

        // --- Panel boków (formularz rezerwacji) --- //
        JPanel rightPanel = new JPanel(new GridLayout(8, 1));
        rightPanel.setPreferredSize(new Dimension(300, 0));

        rightPanel.add(new JLabel("Client ID:"));
        clientIdField = new JTextField();
        rightPanel.add(clientIdField);

        rightPanel.add(new JLabel("Offer ID:"));
        offerIdField = new JTextField();
        rightPanel.add(offerIdField);

        rightPanel.add(new JLabel("Spots:"));
        spotsField = new JTextField();
        rightPanel.add(spotsField);

        JButton reserveBtn = new JButton("Make reservation");
        reserveBtn.addActionListener(e -> makeReservation());
        rightPanel.add(reserveBtn);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        rightPanel.add(messageArea);

        // --- Symulacja dnia ---
        dateLabel = new JLabel("Day: " + TimeSimulator.getInstance().getSimulatedDate());
        rightPanel.add(dateLabel);

    // listener
        TimeSimulator.getInstance().addListener(newDay ->
                SwingUtilities.invokeLater(() ->
                        dateLabel.setText("Day: " + newDay)
                )
        );
        rightPanel.add(dateLabel);

        add(rightPanel, BorderLayout.EAST);

        // --- rezerwacje klienta --- //
        JButton myResBtn = new JButton("Show my reservations");
        myResBtn.addActionListener(e -> showClientReservations());

        add(myResBtn, BorderLayout.SOUTH);

        loadOffers();


    }

    private void loadOffers() {
        try {
            List<Offer> offers = offerService.getAllOffers();

            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Date", "Location", "Max", "Free"},
                    0
            );

            for (Offer o : offers) {
                model.addRow(new Object[]{
                        o.getId(),
                        o.getDate(),
                        o.getLocation(),
                        o.getMaxPlaces(),
                        o.getFreePlaces()
                });
            }

            table.setModel(model);
            messageArea.setText("Offers loaded.");
        } catch (SQLException | DataException e) {
            messageArea.setText("Error: " + e.getMessage());
        }
    }

    private void makeReservation() {
        try {
            int clientId = Integer.parseInt(clientIdField.getText());
            int offerId = Integer.parseInt(offerIdField.getText());
            int spots = Integer.parseInt(spotsField.getText());

            clientService.makeReservation(clientId, offerId, spots);

            messageArea.setText("Reservation created!");
            loadOffers(); // odśwież oferty (freePlaces)

        } catch (NumberFormatException e) {
            messageArea.setText("Enter numeric values!");
        } catch (ValidationException e) {
            messageArea.setText(e.getMessage());
        }
    }

    private void showClientReservations() {
        try {
            int clientId = Integer.parseInt(JOptionPane.showInputDialog(this, "Client ID:"));
            new ClientReservationsWindow(clientId).setVisible(true);

        } catch (NumberFormatException e) {
            messageArea.setText("Enter a valid client ID!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientAppGUI().setVisible(true));
    }


}
