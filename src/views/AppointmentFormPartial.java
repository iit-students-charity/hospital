package views;

import controllers.AppointmentsController;

import org.jdatepicker.impl.JDatePickerImpl;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class AppointmentFormPartial {
    private static final DateFormat FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private AppointmentsController controller;
    private JPanel panel;
    private JTextField patientNameField;
    private JTextField patientSurnameField;
    private JTextField patientCityField;
    private JTextField patientStreetField;
    private JTextField patientBuildingNumberField;
    private JDatePickerImpl patientBirthDateField;
    private JTextField doctorNameField;
    private JTextField doctorSurnameField;
    private JDatePickerImpl dateField;
    private JTextField diagnosisField;

    public AppointmentFormPartial(AppointmentsController controller) {
        this.controller = controller;
        panel = new JPanel(new GridLayout(17, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel patientNameLabel = new JLabel("Patient's name:");
        JLabel patientSurnameLabel = new JLabel("Patient's surname:");
        JLabel patientCityLabel = new JLabel("Patient's city:");
        JLabel patientStreetLabel = new JLabel("Patient's street:");
        JLabel patientBuildingNumberLabel = new JLabel("Patient's building number:");
        JLabel birthDateLabel = new JLabel("Patient's birth date:");
        JLabel doctorNameLabel = new JLabel("Doctor's name:");
        JLabel doctorSurnameLabel = new JLabel("Doctor's surname:");
        JLabel dateLabel = new JLabel("Date:");
        JLabel diagnosisLabel = new JLabel("Diagnosis:");
        patientNameField = new JTextField(20);
        patientSurnameField = new JTextField(20);
        patientCityField = new JTextField(20);
        patientStreetField = new JTextField(20);
        patientBuildingNumberField = new JTextField(20);
        patientBirthDateField = new DatePickerPartial().getDatePicker();
        doctorNameField = new JTextField(20);
        doctorSurnameField = new JTextField(20);
        dateField = new DatePickerPartial().getDatePicker();
        diagnosisField = new JTextField(20);

        panel.add(patientNameLabel);
        panel.add(patientNameField);
        panel.add(patientSurnameLabel);
        panel.add(patientSurnameField);
        panel.add(patientCityLabel);
        panel.add(patientCityField);
        panel.add(patientStreetLabel);
        panel.add(patientStreetField);
        panel.add(patientBuildingNumberLabel);
        panel.add(patientBuildingNumberField);
        panel.add(birthDateLabel);
        panel.add(patientBirthDateField);
        panel.add(doctorNameLabel);
        panel.add(doctorNameField);
        panel.add(doctorSurnameLabel);
        panel.add(doctorSurnameField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(diagnosisLabel);
        panel.add(diagnosisField);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getPatientName() {
        return patientNameField.getText();
    }

    public String getPatientSurname() {
        return patientSurnameField.getText();
    }

    public String getPatientCity() {
        return patientCityField.getText();
    }

    public String getPatientStreet() {
        return patientStreetField.getText();
    }

    public String getPatientBuildingNumber() {
        return patientBuildingNumberField.getText();
    }

    public String getPatientBirthDate() {
        Date date = (Date) patientBirthDateField.getModel().getValue();
        if (date == null) {
            return "";
        }
        return FORMAT.format(date);
    }

    public String getDoctorName() {
        return doctorNameField.getText();
    }

    public String getDoctorSurname() {
        return doctorSurnameField.getText();
    }

    public String getDate() {
        Date date = (Date) dateField.getModel().getValue();
        if (date == null) {
            return "";
        }
        return FORMAT.format(date);
    }

    public String getDiagnosis() {
        return diagnosisField.getText();
    }
}

