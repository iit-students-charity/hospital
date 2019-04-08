package views;

import helpers.DatePickerLabelFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Properties;

public class DatePickerPartial {
    private JDatePickerImpl datePicker;

    public DatePickerPartial() {
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DatePickerLabelFormatter());
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }
}
