package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Appointment
{
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String diagnosis;

    public Appointment() {
        this(new Patient(), new Doctor(), new Date(), "");
    }

    public Appointment(Patient patient, Doctor doctor, Date date, String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
    }

    public Appointment(HashMap<String, String> attributes) {
        this.date = null;
        Date patientBirthDate = null;
        try {
            if (!attributes.get("date").isEmpty()) {
                this.date = DATE_FORMAT.parse(attributes.get("date"));
            }
            if (!attributes.get("patientBirthDate").isEmpty()) {
                patientBirthDate = DATE_FORMAT.parse(attributes.get("patientBirthDate"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address address = new Address(attributes.get("patientCity"), attributes.get("patientStreet"), attributes.get("patientBuildingNumber"));

        this.patient = new Patient(attributes.get("patientName"), attributes.get("patientSurname"), patientBirthDate, address);
        this.doctor = new Doctor(attributes.get("doctorName"), attributes.get("doctorSurname"));
        this.diagnosis = attributes.get("diagnosis");
    }

    public String getPatientFullName() {
        return patient.getFullName();
    }

    public String getPatientName() {
        return patient.getName();
    }

    public String getPatientSurname() {
        return patient.getSurname();
    }

    public String getPatientAddress() {
        return patient.getFullAddress();
    }

    public String getPatientCity() {
        return patient.getAddress().getCity();
    }

    public String getPatientStreet() {
        return patient.getAddress().getStreet();
    }

    public String getPatientBuildingNumber() {
        return patient.getAddress().getBuildingNumber();
    }

    public Date getPatientBirthDate() {
        return patient.getBirthDate();
    }

    public String getPatientBirthDateString() {
        if (getPatientBirthDate() == null) {
            return "";
        }
        return DATE_FORMAT.format(getPatientBirthDate());
    }

    public String getDoctorFullName() {
        return doctor.getFullName();
    }

    public String getDoctorName() {
        return doctor.getName();
    }

    public String getDoctorSurname() {
        return doctor.getSurname();
    }

    public Date getDate() {
        return date;
    }

    public String getDateString() {
        if (getDate() == null) {
            return "";
        }
        return DATE_FORMAT.format(getDate());
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
