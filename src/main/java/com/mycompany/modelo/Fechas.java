package com.mycompany.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
 
public class Fechas {
 
    public static String convert(Calendar gregorianCalendarDate) {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormatted = formattedDate.format(gregorianCalendarDate.getTime());
        return dateFormatted;
    }

    public static LocalDate calToLocalDate(Calendar gregorianCalendarDate) {
        SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy");
        LocalDate dateFormatted = LocalDateTime.ofInstant(gregorianCalendarDate.toInstant(), gregorianCalendarDate.getTimeZone().toZoneId()).toLocalDate();
        return dateFormatted;
    }
}