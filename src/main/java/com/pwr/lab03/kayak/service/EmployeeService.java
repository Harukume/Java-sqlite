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
            return taskDAO.findOpen();
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


    public void closeTask(int taskId) throws DataException {
        try {
            taskDAO.updateStatus(taskId, "closed");
        } catch (SQLException e) {
            throw new DataException("Error closing task", e);
        }
    }
}