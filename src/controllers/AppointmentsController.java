package controllers;

import models.Appointment;
import models.AppointmentsDTO;
import views.*;
import models.AppointmentsLocalStorage;

import java.io.File;
import java.util.List;

public class AppointmentsController {
    private AppointmentsLocalStorage appointments;

    AppointmentsController(AppointmentsLocalStorage appointments) {
        this.appointments = appointments;
    }

    public void create(AppointmentsDTO params) {
        Appointment newRecord = new Appointment(params);
        appointments.addRecord(newRecord);
    }

    public void select(AppointmentsDTO params, SearchWindow window) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        window.update(searchResults);
    }

    public List<Appointment> remove(AppointmentsDTO params) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        List<Appointment> appointments = this.appointments.getRecords();
        appointments.removeAll(searchResults);
        this.appointments.setRecords(appointments);
        return searchResults;
    }

    public void open(File file) {
        appointments.setSourceFile(file);
        appointments.readAll();
    }

    public void save(File file) {
        appointments.setSourceFile(file);
        appointments.commitAll();
    }

    public AppointmentsLocalStorage getAppointments() {
        return appointments;
    }
}
