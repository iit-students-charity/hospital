package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment
{
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
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        return format.format(getPatientBirthDate());
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
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        return format.format(getDate());
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
