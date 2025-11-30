package com.pwr.lab03.kayak.gui;

import com.pwr.lab03.kayak.model.Task;
import com.pwr.lab03.kayak.service.EmployeeService;
import com.pwr.lab03.kayak.util.TimeSimulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeAppGUI extends JFrame{

    private final EmployeeService employeeService = new EmployeeService();

    private JTable taskTable;
    private JTextArea messageArea;
    private JLabel dateLabel;

    public EmployeeAppGUI() {
        setTitle("Employee Application");
        setSize(630, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(900, 0);
        setLayout(new BorderLayout());

        // --- Tabela zleceń ---
        taskTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tasks"));
        add(scrollPane, BorderLayout.CENTER);

        // --- Panel boczny ---
        JPanel rightPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        rightPanel.setPreferredSize(new Dimension(250, 0));

        JButton refreshBtn = new JButton("Refresh Tasks");
        refreshBtn.addActionListener(e -> loadTasks());
        rightPanel.add(refreshBtn);

        JButton startTaskBtn = new JButton("Start Task");
        startTaskBtn.addActionListener(e -> changeTaskStatus("in_progress"));
        rightPanel.add(startTaskBtn);

        JButton completeTaskBtn = new JButton("Complete Task");
        completeTaskBtn.addActionListener(e -> changeTaskStatus("closed"));
        rightPanel.add(completeTaskBtn);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        rightPanel.add(new JScrollPane(messageArea));

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

        // --- Inicjalizacja danych ---
        loadTasks();
    }

    private void loadTasks() {
        try {
            List<Task> tasks = employeeService.viewTasks();
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Organizer ID", "Employee ID", "Offer ID", "Status"},
                    0
            );
            for (Task t : tasks) {
                model.addRow(new Object[]{
                        t.getId(),
                        t.getOrganizerId(),
                        t.getEmployeeId(),
                        t.getOfferId(),
                        t.getStatus()
                });
            }
            taskTable.setModel(model);
            messageArea.setText("Tasks loaded.");
        } catch (Exception e) {
            messageArea.setText("Error loading tasks: " + e.getMessage());
        }
    }

    private void changeTaskStatus(String newStatus) {
        try {
            int selectedRow = taskTable.getSelectedRow();
            if (selectedRow == -1) {
                messageArea.setText("Select a task first!");
                return;
            }
            int taskId = (int) taskTable.getValueAt(selectedRow, 0);
            employeeService.updateTask(taskId, newStatus);

            loadTasks();
            messageArea.setText("Task status updated to " + newStatus +
                    " and related reservations updated accordingly.");
        } catch (Exception e) {
            messageArea.setText("Error updating task: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeAppGUI().setVisible(true));
    }
}
