package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment
{
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String diagnosis;

    Appointment() {
        this(new Patient(), new Doctor(), new Date(), "");
    }

    private Appointment(Patient patient, Doctor doctor, Date date, String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
    }

    public Appointment(AppointmentsDTO attributes) {
        this.date = null;
        Date patientBirthDate = null;
        try {
            if (!attributes.getDate().isEmpty()) {
                this.date = DATE_FORMAT.parse(attributes.getDate());
            }
            if (!attributes.getPatientBirthDate().isEmpty()) {
                patientBirthDate = DATE_FORMAT.parse(attributes.getPatientBirthDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Address address = new Address(attributes.getPatientCity(), attributes.getPatientStreet(), attributes.getPatientBuildingNumber());

        this.patient = new Patient(attributes.getPatientName(), attributes.getPatientSurname(), patientBirthDate, address);
        this.doctor = new Doctor(attributes.getDoctorName(), attributes.getDoctorSurname());
        this.diagnosis = attributes.getDiagnosis();
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

    void setPatient(Patient patient) {
        this.patient = patient;
    }

    void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    void setDate(Date date) {
        this.date = date;
    }

    void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
