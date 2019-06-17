package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsLocalStorage {
    private List<Appointment> records = new ArrayList<Appointment>();
    private File sourceFile;

    public AppointmentsLocalStorage() {}

    public boolean isSourceSet() {
        return (sourceFile != null);
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void readAll() {
        records = new AppointmentsXMLReader(sourceFile).readAll();
        if (records == null) {
            records = new ArrayList<Appointment>();
        }
    }

    public void commitAll() {
        new AppointmentsXMLWriter(sourceFile).writeAll(records);
    }

    public List<Appointment> getRecords() {
        return records;
    }

    public void setRecords(List<Appointment> appointments) {
        this.records = appointments;
    }

    public void addRecord(Appointment appointment) {
        records.add(appointment);
    }

    public List<Appointment> applyFilters(AppointmentsDTO params) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : records) {
            if (appointment.getDiagnosis().matches(".*" + params.getDiagnosis() + ".*") &&
            appointment.getDateString().matches(".*" + params.getDate() + ".*") &&
            appointment.getDoctorSurname().matches(".*" + params.getDoctorSurname() + ".*") &&
            appointment.getDoctorName().matches(".*" + params.getDoctorName() + ".*") &&
            appointment.getPatientBirthDateString().matches(".*" + params.getPatientBirthDate() + ".*") &&
            appointment.getPatientBuildingNumber().matches(".*" + params.getPatientBuildingNumber() + ".*") &&
            appointment.getPatientStreet().matches(".*" + params.getPatientStreet() + ".*") &&
            appointment.getPatientCity().matches(".*" + params.getPatientCity() + ".*") &&
            appointment.getPatientSurname().matches(".*" + params.getPatientSurname() + ".*") &&
            appointment.getPatientName().matches(".*" + params.getPatientName() + ".*")) {
                results.add(appointment);
            }
        }

        return results;
    }
}
