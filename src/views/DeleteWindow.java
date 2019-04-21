package views;

import controllers.AppointmentsController;
import models.AppointmentsDTO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class DeleteWindow {
    private AppointmentsController controller;
    private JFrame deleteWindow;

    public DeleteWindow(AppointmentsController controller) {
        this.controller = controller;
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

    public void show() {
        deleteWindow.setVisible(true);
    }

    public void dispose() {
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
            controller.remove(params);
        };
    }
}
