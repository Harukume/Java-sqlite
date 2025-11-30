package com.pwr.lab03.kayak.gui;

import com.pwr.lab03.kayak.model.Offer;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.service.OfferService;
import com.pwr.lab03.kayak.service.ReservationService;
import com.pwr.lab03.kayak.service.OrganizerService;
import com.pwr.lab03.kayak.utils.TimeSimulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrganizerAppGUI extends JFrame {

    private final OfferService offerService = new OfferService();
    private final ReservationService reservationService = new ReservationService();
    private final OrganizerService organizerService = new OrganizerService();

    private JTable offersTable;
    private JTable reservationsTable;
    private JTextField dateField;
    private JTextField locationField;
    private JTextField maxPlacesField;
    private JTextArea messageArea;
    private JLabel dateLabel;

    public OrganizerAppGUI() {

        setTitle("Organizer Application");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(0, 250);
        setLayout(new BorderLayout());

        // --- Tabela ofert ---
        offersTable = new JTable();
        JScrollPane offersScroll = new JScrollPane(offersTable);
        offersScroll.setBorder(BorderFactory.createTitledBorder("Offers"));
        add(offersScroll, BorderLayout.CENTER);

        // --- Panel boczny (dodawanie ofert, przyciski i taski) ---
        JPanel rightPanel = new JPanel(new GridLayout(15, 1, 5, 5));
        rightPanel.setPreferredSize(new Dimension(300, 0));

        // --- Dodawanie ofert ---
        rightPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        rightPanel.add(dateField);

        rightPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        rightPanel.add(locationField);

        rightPanel.add(new JLabel("Max places:"));
        maxPlacesField = new JTextField();
        rightPanel.add(maxPlacesField);

        JButton addOfferBtn = new JButton("Add Offer");
        addOfferBtn.addActionListener(e -> addOffer());
        rightPanel.add(addOfferBtn);

        JButton deleteBtn = new JButton("Delete Offer");
        deleteBtn.addActionListener(e -> deleteSelectedOffer());
        rightPanel.add(deleteBtn);

        // --- Odświeżanie tabel ---
        JButton refreshOffersBtn = new JButton("Refresh Offers");
        refreshOffersBtn.addActionListener(e -> loadOffers());
        rightPanel.add(refreshOffersBtn);

        JButton refreshResBtn = new JButton("Refresh Reservations");
        refreshResBtn.addActionListener(e -> loadReservations());
        rightPanel.add(refreshResBtn);

        // --- Edycja statusu rezerwacji ---
        JButton editResBtn = new JButton("Edit Reservation Status");
        editResBtn.addActionListener(e -> editReservationStatus());
        rightPanel.add(editResBtn);

        // --- Tworzenie tasków ---
        JButton createTaskBtn = new JButton("Create Task");
        createTaskBtn.addActionListener(e -> createTask());
        rightPanel.add(createTaskBtn);

        messageArea = new JTextArea(5, 20);
        messageArea.setEditable(false);
        rightPanel.add(new JScrollPane(messageArea));

        // --- Symulacja dnia ---
        dateLabel = new JLabel("Day: " + TimeSimulator.getInstance().getSimulatedDate());
    // listener
        TimeSimulator.getInstance().addListener(newDay ->
                SwingUtilities.invokeLater(() -> {
                    dateLabel.setText("Day: " + newDay);
                    loadOffers();
                    loadReservations();
                })
        );
        rightPanel.add(dateLabel);

        add(rightPanel, BorderLayout.EAST);

        // --- Panel dolny (tabela rezerwacji) ---
        reservationsTable = new JTable();
        JScrollPane resScroll = new JScrollPane(reservationsTable);
        resScroll.setBorder(BorderFactory.createTitledBorder("Reservations"));
        resScroll.setPreferredSize(new Dimension(0, 150));
        add(resScroll, BorderLayout.SOUTH);

        // --- Inicjalizacja danych ---
        loadOffers();
        loadReservations();
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
            offersTable.setModel(model);
            messageArea.setText("Offers loaded.");
        } catch (Exception e) {
            messageArea.setText("Error loading offers: " + e.getMessage());
        }
    }

    private void loadReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllReservations();
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Offer ID", "Client ID", "Spots", "Status"},
                    0
            );
            for (Reservation r : reservations) {
                model.addRow(new Object[]{
                        r.getId(),
                        r.getOfferId(),
                        r.getClientId(),
                        r.getNumberOfSeats(),
                        r.getStatus()
                });
            }
            reservationsTable.setModel(model);
        } catch (Exception e) {
            messageArea.setText("Error loading reservations: " + e.getMessage());
        }
    }

    private void addOffer() {
        try {
            String date = dateField.getText();
            String location = locationField.getText();
            int max = Integer.parseInt(maxPlacesField.getText());

            offerService.addOffer(date, location, max);
            messageArea.setText("Offer added!");
            loadOffers();
        } catch (Exception e) {
            messageArea.setText("Error adding offer: " + e.getMessage());
        }
    }

    private void deleteSelectedOffer() {
        int row = offersTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "No offer selected!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int offerId = (int) offersTable.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to delete offer ID=" + offerId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            offerService.deleteOffer(offerId);
            JOptionPane.showMessageDialog(this, "Offer removed.");
            loadOffers(); // ← odświeżenie tabeli
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting offer: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void editReservationStatus() {
        try {
            int selectedRow = reservationsTable.getSelectedRow();
            if (selectedRow == -1) {
                messageArea.setText("Select a reservation first!");
                return;
            }
            int resId = (int) reservationsTable.getValueAt(selectedRow, 0);
            String newStatus = JOptionPane.showInputDialog(this,
                    "Enter new status (created/confirmed/in_progress/completed/cancelled):");
            if (newStatus != null && !newStatus.isBlank()) {
                reservationService.changeStatus(resId, newStatus);
                loadReservations();
                messageArea.setText("Reservation status updated.");
            }
        } catch (Exception e) {
            messageArea.setText("Error updating reservation: " + e.getMessage());
        }
    }

    private void createTask() {
        try {
            int organizerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Organizer ID:"));
            int employeeId = Integer.parseInt(JOptionPane.showInputDialog(this, "Employee ID:"));
            int offerId = Integer.parseInt(JOptionPane.showInputDialog(this, "Offer ID:"));
            organizerService.generateTask(organizerId, employeeId, offerId);
            messageArea.setText("Task created.");
        } catch (Exception e) {
            messageArea.setText("Error creating task: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrganizerAppGUI().setVisible(true));
    }
}
