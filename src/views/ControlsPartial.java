package views;

import controllers.AppointmentsController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class ControlsPartial {
    private AppointmentsController controller;
    private IndexWindow indexWindow;
    private JPanel panel = new JPanel(new GridLayout(15, 1, 0, 10));

    ControlsPartial(AppointmentsController controller, IndexWindow parent) {
        this.controller = controller;
        this.indexWindow = parent;
        JButton addButton = new JButton("Add");
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");
        JButton openButton = new JButton("Open");
        JButton saveButton = new JButton("Save");
        JButton saveAsButton = new JButton("Save as");

        panel.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(".xml","xml"));

        addButton.addActionListener(getAddButtonListener());
        searchButton.addActionListener(getSearchButtonListener());
        deleteButton.addActionListener(getDeleteButtonListener());
        openButton.addActionListener(getOpenButtonListener(fileChooser));
        saveButton.addActionListener(getSaveButtonListener(fileChooser));
        saveAsButton.addActionListener(getSaveAsButtonListener(fileChooser));

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(new JSeparator());
        panel.add(openButton);
        panel.add(saveButton);
        panel.add(saveAsButton);
    }

    private ActionListener getOpenButtonListener(JFileChooser fileChooser) {
        return e -> {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.open(file);
                indexWindow.update();
            }
        };
    }

    private ActionListener getSaveButtonListener(JFileChooser fileChooser) {
        return e -> {
            File file = controller.getAppointments().getSourceFile();
            if (!controller.getAppointments().isSourceSet()) {
                int response = fileChooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                }
            }
            controller.save(file);
        };
    }

    private ActionListener getSaveAsButtonListener(JFileChooser fileChooser) {
        return e -> {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.save(file);
            }
        };
    }

    JPanel getPanel() {
        return panel;
    }

    private ActionListener getAddButtonListener() {
        return e -> new NewWindow(controller, indexWindow).show();
    }

    private ActionListener getSearchButtonListener() {
        return e -> new SearchWindow(controller).show();
    }

    private ActionListener getDeleteButtonListener() {
        return e -> new DeleteWindow(controller, indexWindow).show();
    }
}
