package conglin.serendipity.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String getDateYear(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(date);
    }

    public static String getDateMonth(Date date){
        DateFormat dateFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    public static String getDateDay(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(date);
    }

    public static String getDateTime(Date date){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
}
