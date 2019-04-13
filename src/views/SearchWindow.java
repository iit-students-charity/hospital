package views;

import controllers.AppointmentsController;
import models.Appointment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.HashMap;

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

    public void updateTable(List<Appointment> appointments) {
        table.setData(appointments);
    }

    private ActionListener getCloseButtonListener() {
        return e -> dispose();
    }

    private ActionListener getSearchButtonListener(AppointmentFormPartial form) {
        return e -> {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("patientName", form.getPatientName());
            params.put("patientSurname", form.getPatientSurname());
            params.put("patientCity", form.getPatientCity());
            params.put("patientStreet", form.getPatientStreet());
            params.put("patientBuildingNumber", form.getPatientBuildingNumber());
            params.put("patientBirthDate", form.getPatientBirthDate());
            params.put("doctorName", form.getDoctorName());
            params.put("doctorSurname", form.getDoctorSurname());
            params.put("date", form.getDate());
            params.put("diagnosis", form.getDiagnosis());
            controller.select(params);
        };
    }
}
