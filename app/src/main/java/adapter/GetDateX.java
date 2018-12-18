package adapter;

import java.text.SimpleDateFormat;

public class GetDateX {
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT_12 = "hh:mm:ss a";
    private static final String TIME_FORMAT_24 = "HH:mm:ss";

    public static String getDateString(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    public static String getTime24String(String date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_24);
        return format.format(date);
    }

    public static String getTime12String(String date) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT_12);
        return format.format(date);
    }
}
