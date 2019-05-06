package views;

import controllers.AppointmentsController;
import models.AppointmentsDTO;

import java.awt.event.ActionListener;
import javax.swing.*;

public class NewWindow {
    private AppointmentsController controller;
    private JFrame newWindow;
    private IndexWindow indexWindow;

    public NewWindow(AppointmentsController controller, IndexWindow parent) {
        this.controller = controller;
        this.indexWindow = parent;
        newWindow = new JFrame("New appointment");
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        AppointmentFormPartial form = new AppointmentFormPartial(controller);
        JPanel panel = form.getPanel();

        JButton closeButton = new JButton("Close");
        JButton submitButton = new JButton("Submit");
        closeButton.addActionListener(getCloseButtonListener());
        submitButton.addActionListener(getSubmitButtonListener(form));
        panel.add(closeButton);
        panel.add(submitButton);

        contentPane.add(panel);

        newWindow.setContentPane(contentPane);
        newWindow.pack();
    }

    public void show() {
        newWindow.setVisible(true);
    }

    public void dispose() {
        newWindow.dispose();
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
            controller.create(params, indexWindow);
        };
    }
}
