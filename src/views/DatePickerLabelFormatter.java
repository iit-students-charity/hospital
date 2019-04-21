package views;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private String pattern = "dd.MM.yyyy";
    private SimpleDateFormat format = new SimpleDateFormat(pattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return format.parseObject(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar calendar = (Calendar) value;
            return format.format(calendar.getTime());
        }
        return "";
    }
}
