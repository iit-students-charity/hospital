package models;

import views.Alert;

import java.io.*;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class AppointmentsXMLWriter {
    private File file;
    private static final String DEFAULT_STRUCTURE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<appointments>\n</appointments>";

    public AppointmentsXMLWriter(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(DEFAULT_STRUCTURE);
            fileWriter.close();
            this.file = file;
        } catch (IOException e) {
            new Alert("Cannot write to file.\nInsure it's in right format and you have write permissions.");
            e.printStackTrace();
        }
    }

    public void writeAll(List<Appointment> appointments) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            Node appointmentsTag = document.getElementsByTagName("appointments").item(0);
            removeAll(document, Node.ELEMENT_NODE, "appointment");

            for(Appointment appointment : appointments) {
                Element appointmentElement = document.createElement("appointment");
                appointmentsTag.appendChild(appointmentElement);

                Element patientElement = document.createElement("patient");

                Element patientName = document.createElement("name");
                patientName.appendChild(document.createTextNode(appointment.getPatientName()));
                patientElement.appendChild(patientName);

                Element patientSurname = document.createElement("surname");
                patientSurname.appendChild(document.createTextNode(appointment.getPatientSurname()));
                patientElement.appendChild(patientSurname);

                Element patientBirthDate = document.createElement("birthDate");
                patientBirthDate.appendChild(document.createTextNode(appointment.getPatientBirthDateString()));
                patientElement.appendChild(patientBirthDate);

                Element addressElement = document.createElement("address");

                Element addressCity = document.createElement("city");
                addressCity.appendChild(document.createTextNode(appointment.getPatientCity()));
                addressElement.appendChild(addressCity);

                Element addressStreet = document.createElement("street");
                addressStreet.appendChild(document.createTextNode(appointment.getPatientStreet()));
                addressElement.appendChild(addressStreet);

                Element addressBuildingNumber = document.createElement("buildingNumber");
                addressBuildingNumber.appendChild(document.createTextNode(appointment.getPatientBuildingNumber()));
                addressElement.appendChild(addressBuildingNumber);

                patientElement.appendChild(addressElement);

                appointmentElement.appendChild(patientElement);

                Element doctorElement = document.createElement("doctor");

                Element doctorName = document.createElement("name");
                doctorName.appendChild(document.createTextNode(appointment.getDoctorName()));
                doctorElement.appendChild(doctorName);

                Element doctorSurname = document.createElement("surname");
                doctorSurname.appendChild(document.createTextNode(appointment.getDoctorSurname()));
                doctorElement.appendChild(doctorSurname);

                appointmentElement.appendChild(doctorElement);

                Element appointmentDate = document.createElement("date");
                appointmentDate.appendChild(document.createTextNode(appointment.getDateString()));
                appointmentElement.appendChild(appointmentDate);

                Element diagnosis = document.createElement("diagnosis");
                diagnosis.appendChild(document.createTextNode(appointment.getDiagnosis()));
                appointmentElement.appendChild(diagnosis);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            new Alert("Cannot write to file.\nInsure it's in right format and you have write permissions.");
            e.printStackTrace();
        }
    }

    private void removeAll(Node node, short nodeType, String name) {
        if (node.getNodeType() == nodeType && (name == null || node.getNodeName().equals(name))) {
            node.getParentNode().removeChild(node);
        } else {
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                removeAll(list.item(i), nodeType, name);
            }
        }
    }
}
