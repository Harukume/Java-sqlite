package com.pwr.lab03.kayak.service;

import com.pwr.lab03.kayak.dao.ReservationDAO;
import com.pwr.lab03.kayak.dao.TaskDAO;
import com.pwr.lab03.kayak.model.Reservation;
import com.pwr.lab03.kayak.model.Task;
import com.pwr.lab03.kayak.exception.DataException;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final TaskDAO taskDAO = new TaskDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    public List<Task> viewTasks() throws DataException {
        try {
            return taskDAO.findAll();
        } catch (SQLException e) {
            throw new DataException("Error fetching tasks", e);
        }
    }

    public void updateReservationStatus(int reservationId, String status) throws DataException {
        try {
            reservationDAO.updateStatus(reservationId, status);
        } catch (SQLException e) {
            throw new DataException("Error updating reservation status", e);
        }
    }

    /**
     * Zmienia status tasku i automatycznie aktualizuje statusy powiązanych rezerwacji
     */
    public void updateTask(int taskId, String taskStatus) throws DataException {
        try {
            Task task = taskDAO.findById(taskId);
            if (task == null) throw new DataException("Task not found");

            switch (taskStatus) {
                case "open":
                    taskDAO.updateStatus(taskId, "open");
                    break;
                case "in_progress":
                    taskDAO.updateStatus(taskId, "in_progress");
                    // Zmiana statusu wszystkich rezerwacji powiązanych z ofertą na "in_progress"
                    startReservationsByOffer(task.getOfferId());
                    break;
                case "closed":
                    taskDAO.updateStatus(taskId, "closed");
                    // Zmiana statusu wszystkich rezerwacji powiązanych z ofertą na "realized"
                    completeReservationsByOffer(task.getOfferId());
                    break;
                default:
                    throw new DataException("Invalid task status");
            }
        } catch (SQLException e) {
            throw new DataException("Error updating task", e);
        }
    }

    /**
     * Ustawia status rezerwacji powiązanych z ofertą na "in_progress"
     */
    private void startReservationsByOffer(int offerId) throws DataException {
        try {
            List<Reservation> reservations = reservationDAO.findByOffer(offerId);
            for (Reservation r : reservations) {
                if ("confirmed".equals(r.getStatus())) {
                    reservationDAO.updateStatus(r.getId(), "in_progress");
                }
            }
        } catch (SQLException e) {
            throw new DataException("Error starting reservations", e);
        }
    }

    /**
     * Ustawia status rezerwacji powiązanych z ofertą na "realized"
     */
    private void completeReservationsByOffer(int offerId) throws DataException {
        try {
            List<Reservation> reservations = reservationDAO.findByOffer(offerId);
            for (Reservation r : reservations) {
                if ("in_progress".equals(r.getStatus())) {
                    reservationDAO.updateStatus(r.getId(), "realized");
                }
            }
        } catch (SQLException e) {
            throw new DataException("Error completing reservations", e);
        }
    }
}
