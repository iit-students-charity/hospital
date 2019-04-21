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
        List<Appointment> results;
        results = applyPatientNameFilter(params.getPatientName());
        results = applyPatientSurnameFilter(results, params.getPatientSurname());
        results = applyPatientCityFilter(results, params.getPatientCity());
        results = applyPatientStreetFilter(results, params.getPatientStreet());
        results = applyPatientBuildingNumberFilter(results, params.getPatientBuildingNumber());
        results = applyPatientBirthDateFilter(results, params.getPatientBirthDate());
        results = applyDoctorNameFilter(results, params.getDoctorName());
        results = applyDoctorSurnameFilter(results, params.getDoctorSurname());
        results = applyDateFilter(results, params.getDate());
        results = applyDiagnosisFilter(results, params.getDiagnosis());
        return results;
    }

    private List<Appointment> applyDiagnosisFilter(List<Appointment> data, String diagnosis) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getDiagnosis().matches(".*" + diagnosis + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyDiagnosisFilter(String diagnosis) {
        return applyDiagnosisFilter(records, diagnosis);
    }

    private List<Appointment> applyDateFilter(List<Appointment> data, String date) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getDateString().matches(".*" + date + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyDateFilter(String date) {
        return applyDateFilter(records, date);
    }

    private List<Appointment> applyDoctorSurnameFilter(List<Appointment> data, String doctorSurname) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getDoctorSurname().matches(".*" + doctorSurname + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyDoctorSurnameFilter(String doctorSurname) {
        return applyDoctorSurnameFilter(records, doctorSurname);
    }

    private List<Appointment> applyDoctorNameFilter(List<Appointment> data, String doctorName) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getDoctorName().matches(".*" + doctorName + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyDoctorNameFilter(String doctorName) {
        return applyDoctorNameFilter(records, doctorName);
    }

    private List<Appointment> applyPatientBirthDateFilter(List<Appointment> data, String patientBirthDate) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientBirthDateString().matches(".*" + patientBirthDate + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientBirthDateFilter(String patientBirthDate) {
        return applyPatientBirthDateFilter(records, patientBirthDate);
    }

    private List<Appointment> applyPatientBuildingNumberFilter(List<Appointment> data, String patientBuildingNumber) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientBuildingNumber().matches(".*" + patientBuildingNumber + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientBuildingNumberFilter(String patientBuildingNumber) {
        return applyPatientBuildingNumberFilter(records, patientBuildingNumber);
    }

    private List<Appointment> applyPatientStreetFilter(List<Appointment> data, String patientStreet) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientStreet().matches(".*" + patientStreet + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientStreetFilter(String patientStreet) {
        return applyPatientStreetFilter(records, patientStreet);
    }

    private List<Appointment> applyPatientCityFilter(List<Appointment> data, String patientCity) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientCity().matches(".*" + patientCity + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientCityFilter(String patientCity) {
        return applyPatientCityFilter(records, patientCity);
    }

    private List<Appointment> applyPatientSurnameFilter(List<Appointment> data, String patientSurname) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientSurname().matches(".*" + patientSurname + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientSurnameFilter(String patientSurname) {
        return applyPatientSurnameFilter(records, patientSurname);
    }

    private List<Appointment> applyPatientNameFilter(List<Appointment> data, String patientName) {
        List<Appointment> results = new ArrayList<Appointment>();
        for(Appointment appointment : data) {
            if (appointment.getPatientName().matches(".*" + patientName + ".*")) {
                results.add(appointment);
            }
        }
        return results;
    }

    private List<Appointment> applyPatientNameFilter(String patientName) {
        return applyPatientNameFilter(records, patientName);
    }
}