package views;

import controllers.AppointmentsController;

import javax.swing.*;
import java.awt.*;

public class IndexWindow {
    private AppointmentsController controller;
    private JFrame mainWindow;
    private TablePartial table;

    public IndexWindow(AppointmentsController controller) {
        this.controller = controller;
        mainWindow = new JFrame("Appointments");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        table = new TablePartial(controller.getAppointments().getRecords());
        contentPane.add(table.getPanel(), BorderLayout.CENTER);
        contentPane.add(new ControlsPartial(controller).getPanel(), BorderLayout.WEST);

        mainWindow.setJMenuBar(new MenuPartial(controller).getMenuBar());
        mainWindow.setContentPane(contentPane);
        mainWindow.pack();
    }

    public void show() {
        mainWindow.setVisible(true);
    }

    public void dispose() {
        mainWindow.dispose();
    }

    public void update() {
        table.setData(controller.getAppointments().getRecords());
    }
}
