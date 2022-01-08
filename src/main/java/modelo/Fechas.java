package modelo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
 
public class Fechas {
 
    public static String convert(GregorianCalendar gregorianCalendarDate) {
        // Creating an object of SimpleDateFormat
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MMM-dd");
 
        // Use format() method to change the format
        // Using getTime() method,
        // this required date is passed
        // to format() method
        String dateFormatted = formattedDate.format(gregorianCalendarDate.getTime());
 
        // Displaying grogorian date ia SimpleDateFormat
        return dateFormatted;
    }
}