package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattingHelpers {

    public static String renderDateDBToUI(String dateFromDB){
        DateFormat formatDb = null;
        DateFormat formatUi = null;
        Date date = null;

        try {
            formatDb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            formatUi = new SimpleDateFormat("dd-MMM-yyyy");
            date = formatDb.parse(dateFromDB);
        } catch (Exception e) {
            System.out.println("Incorrect format: " + e.getMessage());
        }

        assert formatUi != null;
        return formatUi.format(date).toString();
    }

    public static String renderDate(String dateFromDB){
        DateFormat formatDb = null;
        DateFormat formatUi = null;
        Date date = null;

        try {
            formatDb = new SimpleDateFormat("yyyy-MM-dd");
            formatUi = new SimpleDateFormat("dd MMM yyyy");
            date = formatDb.parse(dateFromDB);
        } catch (Exception e) {
            System.out.println("Incorrect format: " + e.getMessage());
        }

        assert formatUi != null;
        return formatUi.format(date).toString();
    }
}
