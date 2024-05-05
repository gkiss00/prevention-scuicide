package backend.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public final class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH:mm:ss";
    private DateUtil(){}

    public static Date setDateToCurrentTimeZone(Date utcDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            String dateString = simpleDateFormat.format(utcDate);
            log.info(dateString);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            return sdf.parse(dateString);
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getCurrentDate(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    public static String getStartDateForStats(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -12);
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    public static String getEndDateForStats(String format) {
        Calendar calendar = Calendar.getInstance();
        int lastDateOfMonth = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DATE, lastDateOfMonth);
        calendar.add(Calendar.MONTH, 2);
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }
}
