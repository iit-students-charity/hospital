package models;

import views.Alert;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.xml.sax.SAXException;

class AppointmentsXMLReader {
    private AppointmentsHandler handler;
    private SAXParser parser;
    private File file;

    AppointmentsXMLReader(File file) {
        this.file = file;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        handler = new AppointmentsHandler();
    }

    List<Appointment> readAll() {
        try {
            parser.parse(file, handler);
        } catch (SAXException | IOException e) {
            new Alert("Cannot open file.\nEnsure it's in right format and you have read permissions.");
            e.printStackTrace();
            return null;
        }
        return handler.getAppointments();
    }
}
