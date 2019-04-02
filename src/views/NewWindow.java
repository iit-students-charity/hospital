package views;

import controllers.AppointmentsController;

import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class NewWindow {
    private AppointmentsController controller;
    private JFrame newWindow;

    public NewWindow(AppointmentsController controller) {
        this.controller = controller;
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
            controller.create(params);
        };
    }
}
