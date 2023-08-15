package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getCurrentTime() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String formattedDate = String.format("%02d_%02d_%04d_%02d_%02d", day, month, year, hour, minute);
        return formattedDate;
    }
}
