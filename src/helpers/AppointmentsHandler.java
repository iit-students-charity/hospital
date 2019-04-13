package helpers;

import models.Address;
import models.Appointment;
import models.Doctor;
import models.Patient;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class AppointmentsHandler extends DefaultHandler {
    private List<Appointment> appointments;
    private Appointment appointment;
    private Patient patient;
    private Address address;
    private Doctor doctor;
    private String currentElement;
    private boolean isPatient;
    private boolean isAddress;
    private boolean isDoctor;


    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if (qName.equals("appointments")) {
            appointments = new ArrayList<Appointment>();
        }
        if (qName.equals("appointment")) {
            appointment = new Appointment();
        }
        if (qName.equals("patient")) {
            patient = new Patient();
            isPatient = true;
        }
        if (qName.equals("address")) {
            address = new Address();
            isAddress = true;
        }
        if (qName.equals("doctor")) {
            doctor = new Doctor();
            isDoctor = true;
        }
        currentElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equals("appointment")) {
            appointments.add(appointment);
        }
        if (qName.equals("patient")) {
            appointment.setPatient(patient);
            isPatient = false;
        }
        if (qName.equals("address")) {
            patient.setAddress(address);
            isAddress = false;
        }
        if (qName.equals("doctor")) {
            appointment.setDoctor(doctor);
            isDoctor = false;
        }
        currentElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentElement.equals("name") && isPatient) {
            patient.setName(text(ch, start, length));
        }
        if (currentElement.equals("surname") && isPatient) {
            patient.setSurname(text(ch, start, length));
        }
        if (currentElement.equals("city") && isAddress) {
            address.setCity(text(ch, start, length));
        }
        if (currentElement.equals("street") && isAddress) {
            address.setStreet(text(ch, start, length));
        }
        if (currentElement.equals("buildingNumber") && isAddress) {
            address.setBuildingNumber(text(ch, start, length));
        }
        if (currentElement.equals("birthDate") && isPatient) {
            patient.setBirthDate(getDate(text(ch, start, length)));
        }
        if (currentElement.equals("name") && isDoctor) {
            doctor.setName(text(ch, start, length));
        }
        if (currentElement.equals("surname") && isDoctor) {
            doctor.setSurname(text(ch, start, length));
        }
        if (currentElement.equals("date")) {
            appointment.setDate(getDate(text(ch, start, length)));
        }
        if (currentElement.equals("diagnosis")) {
            appointment.setDiagnosis(text(ch, start, length));
        }
    }

    private String text(char[] ch, int start, int length) {
        return new String(ch, start, length);
    }

    private Date getDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
