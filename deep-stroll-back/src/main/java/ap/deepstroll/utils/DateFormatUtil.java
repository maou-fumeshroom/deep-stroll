package ap.deepstroll.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateFormatUtil {

    private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getDate(Timestamp t) {
        sdf.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        return sdf.format(t);
    }
    
}
