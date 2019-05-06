package views;

import controllers.AppointmentsController;
import models.Appointment;
import models.AppointmentsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchWindow {
    private AppointmentsController controller;
    private JFrame searchWindow;
    private TablePartial table;

    public SearchWindow(AppointmentsController controller) {
        this.controller = controller;
        searchWindow = new JFrame("Search");
        searchWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchWindow.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        AppointmentFormPartial form = new AppointmentFormPartial(controller);
        JPanel panel = form.getPanel();

        JButton closeButton = new JButton("Close");
        JButton searchButton = new JButton("Search");
        closeButton.addActionListener(getCloseButtonListener());
        searchButton.addActionListener(getSearchButtonListener(form));
        panel.add(closeButton);
        panel.add(searchButton);

        table = new TablePartial(controller.getAppointments().getRecords());
        contentPane.add(table.getPanel(), BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.WEST);

        searchWindow.setContentPane(contentPane);
        searchWindow.pack();
    }

    public void show() {
        searchWindow.setVisible(true);
    }

    public void dispose() {
        searchWindow.dispose();
    }

    public void update(List<Appointment> appointments) {
        table.setData(appointments);
    }

    private ActionListener getCloseButtonListener() {
        return e -> dispose();
    }

    private ActionListener getSearchButtonListener(AppointmentFormPartial form) {
        return e -> {
            AppointmentsDTO params = new AppointmentsDTO(
                    form.getPatientName(),
                    form.getPatientSurname(),
                    form.getPatientCity(),
                    form.getPatientStreet(),
                    form.getPatientBuildingNumber(),
                    form.getPatientBirthDate(),
                    form.getDoctorName(),
                    form.getDoctorSurname(),
                    form.getDate(),
                    form.getDiagnosis()
            );
            controller.select(params, this);
        };
    }
}
