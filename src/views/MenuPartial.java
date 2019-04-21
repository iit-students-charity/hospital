package views;

import controllers.AppointmentsController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;

class MenuPartial {
    private JMenuBar menuBar;
    private AppointmentsController controller;

    public MenuPartial(AppointmentsController controller) {
        this.controller = controller;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(".xml","xml"));

        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save as");
        openItem.addActionListener(getOpenItemListener(fileChooser));
        saveItem.addActionListener(getSaveItemListener(fileChooser));
        saveAsItem.addActionListener(getSaveAsItemListener(fileChooser));
        menu.add(openItem);
        menu.add(saveItem);
        menu.add(saveAsItem);
        menuBar.add(menu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    private ActionListener getOpenItemListener(JFileChooser fileChooser) {
        return e -> {
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.open(file);
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
