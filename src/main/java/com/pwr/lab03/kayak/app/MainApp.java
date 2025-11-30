package com.pwr.lab03.kayak.app;

import com.pwr.lab03.kayak.gui.ClientAppGUI;
import com.pwr.lab03.kayak.gui.EmployeeAppGUI;
import com.pwr.lab03.kayak.gui.OrganizerAppGUI;
import com.pwr.lab03.kayak.gui.TimeControlWindow;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OrganizerAppGUI().setVisible(true);
            new EmployeeAppGUI().setVisible(true);
            new ClientAppGUI().setVisible(true);
            new TimeControlWindow().setVisible(true);

        });
    }
}
