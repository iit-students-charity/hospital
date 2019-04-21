package controllers;

import models.Appointment;
import models.AppointmentsDTO;
import views.*;
import database.AppointmentsLocalStorage;

import java.io.File;
import java.util.List;

public class AppointmentsController {
    private AppointmentsLocalStorage appointments;
    private IndexWindow indexWindow;
    private SearchWindow searchWindow;

    public AppointmentsController(AppointmentsLocalStorage appointments) {
        this.appointments = appointments;
    }

    public void index(IndexWindow indexWindow) {
        this.indexWindow = indexWindow;
        indexWindow.show();
    }

    public void search(SearchWindow searchWindow) {
        this.searchWindow = searchWindow;
        searchWindow.show();
    }

    public void create(AppointmentsDTO params) {
        Appointment newRecord = new Appointment(params);
        appointments.addRecord(newRecord);
        updateWindows();
    }

    public void select(AppointmentsDTO params) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        searchWindow.update(searchResults);
    }

    public List<Appointment> remove(AppointmentsDTO params) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        List<Appointment> appointments = this.appointments.getRecords();
        appointments.removeAll(searchResults);
        this.appointments.setRecords(appointments);
        updateWindows();
        return searchResults;
    }

    public void open(File file) {
        appointments.setSourceFile(file);
        appointments.readAll();
        updateWindows();
    }

    public void save(File file) {
        appointments.setSourceFile(file);
        appointments.commitAll();
    }

    public AppointmentsLocalStorage getAppointments() {
        return appointments;
    }

    private void updateWindows() {
        if (indexWindow != null) {
            indexWindow.update();
        }
        if (searchWindow != null) {
            searchWindow.update(appointments.getRecords());
        }
    }
}
