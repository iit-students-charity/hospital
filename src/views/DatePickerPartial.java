package views;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Properties;

class DatePickerPartial {
    private JDatePickerImpl datePicker;

    DatePickerPartial() {
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl(datePanel, new DatePickerLabelFormatter());
    }

    JDatePickerImpl getDatePicker() {
        return datePicker;
    }
}
