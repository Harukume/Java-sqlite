package com.pwr.lab03.kayak.gui;

import com.pwr.lab03.kayak.util.TimeSimulator;

import javax.swing.*;
import java.awt.*;

public class TimeControlWindow extends JFrame {

    private JLabel dateLabel;

    public TimeControlWindow() {
        setTitle("Time Simulator");
        setSize(300, 150);
        setLocation(0, 0);
        setLayout(new GridLayout(3, 1));

        dateLabel = new JLabel("Day: " + TimeSimulator.getInstance().getSimulatedDate(), SwingConstants.CENTER);
        add(dateLabel);

        JButton nextDayBtn = new JButton("Next Day");
        nextDayBtn.addActionListener(e -> TimeSimulator.getInstance().advanceDays(1));
        add(nextDayBtn);

        JButton nextWeekBtn = new JButton("Next 7 Days");
        nextWeekBtn.addActionListener(e -> TimeSimulator.getInstance().advanceDays(7));
        add(nextWeekBtn);

        TimeSimulator.getInstance().addListener(newDate ->
                SwingUtilities.invokeLater(() -> dateLabel.setText("Day: " + newDate))
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimeControlWindow().setVisible(true));
    }
}
