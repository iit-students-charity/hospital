package controllers;

import models.AppointmentsLocalStorage;
import views.IndexWindow;

public class ApplicationController {
    public static void main(String[] args) {
        AppointmentsLocalStorage appointments = new AppointmentsLocalStorage();
        AppointmentsController controller = new AppointmentsController(appointments);
        new IndexWindow(controller).show();
    }
}
