package views;

import controllers.AppointmentsController;
import models.Appointment;
import models.AppointmentsDTO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

class DeleteWindow {
    private AppointmentsController controller;
    private IndexWindow indexWindow;
    private JFrame deleteWindow;

    DeleteWindow(AppointmentsController controller, IndexWindow parent) {
        this.controller = controller;
        this.indexWindow = parent;
        deleteWindow = new JFrame("Delete");
        deleteWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteWindow.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        AppointmentFormPartial form = new AppointmentFormPartial(controller);
        JPanel panel = form.getPanel();

        JButton closeButton = new JButton("Close");
        JButton deleteButton = new JButton("Delete");
        closeButton.addActionListener(getCloseButtonListener());
        deleteButton.addActionListener(getSubmitButtonListener(form));
        panel.add(closeButton);
        panel.add(deleteButton);

        contentPane.add(panel);

        deleteWindow.setContentPane(contentPane);
        deleteWindow.pack();
    }

    void show() {
        deleteWindow.setVisible(true);
    }

    void dispose() {
        deleteWindow.dispose();
    }

    private ActionListener getCloseButtonListener() {
        return e -> dispose();
    }

    private ActionListener getSubmitButtonListener(AppointmentFormPartial form) {
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
            List<Appointment> removedRecords = controller.remove(params);
            indexWindow.update();
            new Alert(getRemovedRecordsText(removedRecords));
        };
    }

    private String getRemovedRecordsText(List<Appointment> removedRecords) {
        if (removedRecords.size() == 0) {
            return "No records removed.";
        }
        String text = removedRecords.size() + " appointments removed:\n";
        for (int index = 0; index < removedRecords.size(); index++) {
            Appointment appointment = removedRecords.get(index);
            text += appointment.getPatientSurname() + " at the doctor " + appointment.getDoctorSurname() + " " +
                    appointment.getDateString() + "\n";
            if (index >= 9) {
                return text + "And " + (removedRecords.size() - index - 1) + " more.";
            }
        }
        return text;
    }
}
