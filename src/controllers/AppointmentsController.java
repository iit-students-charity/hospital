package controllers;

import models.Appointment;
import models.AppointmentsDTO;
import views.*;
import database.AppointmentsLocalStorage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;
import java.util.HashMap;

public class AppointmentsController {
    private AppointmentsLocalStorage appointments;
    private IndexWindow indexWindow;
    private SearchWindow searchWindow;

    public AppointmentsController() {
        appointments = new AppointmentsLocalStorage();
    }

    public void index() {
        indexWindow = new IndexWindow(this);
        indexWindow.show();
    }

    public void newRecord() {
        new NewWindow(this).show();
    }

    public void search() {
        searchWindow = new SearchWindow(this);
        searchWindow.show();
    }

    public void delete() {
        new DeleteWindow(this).show();
    }

    public void create(AppointmentsDTO params) {
        Appointment newRecord = new Appointment(params);
        appointments.addRecord(newRecord);
        indexWindow.updateTable();
    }

    public void select(AppointmentsDTO params) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        searchWindow.updateTable(searchResults);
    }

    public void remove(AppointmentsDTO params) {
        List<Appointment> searchResults = appointments.applyFilters(params);
        List<Appointment> appointments = this.appointments.getRecords();
        appointments.removeAll(searchResults);
        this.appointments.setRecords(appointments);
        new Alert(getRemovedRecordsList(searchResults));
        indexWindow.updateTable();
    }

    public void open(File file) {
        appointments.setSourceFile(file);
        appointments.readAll();
        indexWindow.updateTable();
    }

    public void save(File file) {
        appointments.setSourceFile(file);
        appointments.commitAll();
    }

    public void save() {
        if (appointments.isSourceSet()) {
            appointments.commitAll();
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter(".xml","xml"));
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();;
                save(file);
            }
        }
    }

    public AppointmentsLocalStorage getAppointments() {
        return appointments;
    }

    private String getRemovedRecordsList(List<Appointment> removedRecords) {
        if (removedRecords.size() == 0) {
            return "No records removed.";
        }
        String text = removedRecords.size() + " appointments removed:\n";
        for (int index = 0; index < removedRecords.size(); index++) {
            Appointment appointment = removedRecords.get(index);
            text += appointment.getPatientSurname() + " at the doctor " + appointment.getDoctorSurname() + " " +
                    appointment.getDateString() + "\n";
            if (index >= 9) {
                return text + "And " + (removedRecords.size() - index - 1) + " more.";
            }
        }
        return text;
    }
}
