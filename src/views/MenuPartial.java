package views;

import controllers.AppointmentsController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;

class MenuPartial {
    private JMenuBar menuBar;
    private AppointmentsController controller;
    private IndexWindow indexWindow;

    MenuPartial(AppointmentsController controller, IndexWindow parent) {
        this.controller = controller;
        this.indexWindow = parent;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(".xml","xml"));

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save as");
        JMenuItem addItem = new JMenuItem("Add");
        JMenuItem searchItem = new JMenuItem("Search");
        JMenuItem deleteItem = new JMenuItem("Delete");

        openItem.addActionListener(getOpenItemListener(fileChooser));
        saveItem.addActionListener(getSaveItemListener(fileChooser));
        saveAsItem.addActionListener(getSaveAsItemListener(fileChooser));
        addItem.addActionListener(getAddItemListener());
        searchItem.addActionListener(getSearchItemListener());
        deleteItem.addActionListener(getDeleteItemListener());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        editMenu.add(addItem);
        editMenu.add(searchItem);
        editMenu.add(deleteItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }

    JMenuBar getMenuBar() {
        return menuBar;
    }

    private ActionListener getAddItemListener() {
        return e -> {
            new NewWindow(controller, indexWindow).show();
        };
    }

    private ActionListener getSearchItemListener() {
        return e -> {
            new SearchWindow(controller).show();
        };
    }

    private ActionListener getDeleteItemListener() {
        return e -> {
            new DeleteWindow(controller, indexWindow).show();
        };
    }

    private ActionListener getOpenItemListener(JFileChooser fileChooser) {
        return e -> {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.open(file);
                indexWindow.update();
            }
        };
    }

    private ActionListener getSaveItemListener(JFileChooser fileChooser) {
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

    private ActionListener getSaveAsItemListener(JFileChooser fileChooser) {
        return e -> {
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.save(file);
            }
        };
    }
}
