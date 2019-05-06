package views;

import models.Appointment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TablePartial {
    private JPanel panel = new JPanel(new BorderLayout());
    private DefaultTableModel tableModel = getModel();
    private JLabel currentPageLabel;
    private JLabel totalRecordsLabel;
    private JTextField updatePerPageField;
    private List<Appointment> appointments;
    private int page = 1;
    private int perPage = 30;

    TablePartial() {
        this(new ArrayList<Appointment>());
    }

    TablePartial(List<Appointment> appointments) {
        this.appointments = appointments;

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        String[] columnNames = { "Full name", "Address", "Birth date", "Doctor's full name", "Appointment date", "Diagnosis" };
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);
        scrollPane.setPreferredSize(new Dimension(1200, 500));

        JButton nextButton = new JButton(">");
        nextButton.addActionListener(getNextButtonListener(this));
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(getPrevButtonListener(this));
        JButton lastPageButton = new JButton("Last");
        lastPageButton.addActionListener(getLastPageButtonListener(this));
        JButton firstPageButton = new JButton("First");
        firstPageButton.addActionListener(getFirstPageButtonListener(this));
        currentPageLabel = new JLabel();
        JLabel updatePerPageLabel = new JLabel("Per page:");
        totalRecordsLabel = new JLabel("Total records: " + appointments.size());
        updatePerPageField = new JTextField(String.valueOf(perPage), 4);
        updatePerPageField.getDocument().addDocumentListener(getUpdatePerPageListener(this));
        JPanel pageControlPanel = new JPanel();
        pageControlPanel.add(updatePerPageLabel);
        pageControlPanel.add(updatePerPageField);
        pageControlPanel.add(firstPageButton);
        pageControlPanel.add(prevButton);
        pageControlPanel.add(currentPageLabel);
        pageControlPanel.add(nextButton);
        pageControlPanel.add(lastPageButton);
        pageControlPanel.add(totalRecordsLabel);


        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(pageControlPanel, BorderLayout.SOUTH);
        rerender();
    }

    void setData(List<Appointment> appointments) {
        this.appointments = appointments;
        rerender();
    }

    void rerender() {
        List<Appointment> appointments = dataToDisplay(page, perPage);
        tableModel.setRowCount(0);
        for (Appointment appointment : appointments) {
            Object[] row = new Object[] {
                    appointment.getPatientFullName(),
                    appointment.getPatientAddress(),
                    appointment.getPatientBirthDateString(),
                    appointment.getDoctorFullName(),
                    appointment.getDateString(),
                    appointment.getDiagnosis()
            };
            tableModel.addRow(row);
        }
        currentPageLabel.setText(getPage() + "/" + pageCount());
        totalRecordsLabel.setText("Total records: " + this.appointments.size());
    }

    JPanel getPanel() {
        return panel;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getPage() {
        return page;
    }

    public int recordCount() {
        return appointments.size();
    }

    public int pageCount() {
        if (perPage == 0) {
            return 1;
        } else {
            return (int) Math.ceil((double)recordCount() / perPage);
        }
    }

    public void setPerPage(int perPage) {
        if (perPage > recordCount()) {
            perPage = recordCount();
        } else if (perPage < 0) {
            perPage = 0;
        }
        this.perPage = perPage;
        rerender();
    }

    public void setPage(int page) {
        if (page > pageCount()){
            page = pageCount();
        } else if (page <= 0) {
            page = 1;
        }
        this.page = page;
        rerender();
    }

    private ActionListener getNextButtonListener(TablePartial table) {
        return e -> {
            table.setPage(table.getPage() + 1);
        };
    }

    private ActionListener getPrevButtonListener(TablePartial table) {
        return e -> {
            table.setPage(table.getPage() - 1);
        };
    }

    private ActionListener getLastPageButtonListener(TablePartial table) {
        return e -> table.setPage(table.pageCount());
    }

    private ActionListener getFirstPageButtonListener(TablePartial table) {
        return e -> table.setPage(1);
    }

    private DefaultTableModel getModel() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private DocumentListener getUpdatePerPageListener(TablePartial table) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actionForPageListener(table);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actionForPageListener(table);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actionForPageListener(table);
            }
        };
    }

    private void actionForPageListener(TablePartial table) {
        String newPerPage = updatePerPageField.getText();
        newPerPage = newPerPage.equals("") ? "0" : newPerPage;
        table.setPerPage(Integer.valueOf(newPerPage));
        table.setPage(1);
    }

    private List<Appointment> dataToDisplay(int page, int perPage) {
        int from = (page - 1) * perPage;
        int to = from + perPage;
        to = to > appointments.size() ? appointments.size() : to;
        return new ArrayList<Appointment>(appointments.subList(from, to));
    }
}
