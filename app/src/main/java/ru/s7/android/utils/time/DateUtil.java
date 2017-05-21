package ru.s7.android.utils.time;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.s7.android.R;

/**
 * The type Date util.
 *
 * @author celikindv
 * @since 24/03/2017. <p> Class responsible for working with date and date format.
 */
public class DateUtil {
    private FastDateFormat formatterDay;
    private FastDateFormat formatterWeek;
    private FastDateFormat formatterMonth;
    private FastDateFormat formatterYear;
    private FastDateFormat formatterMonthYear;
    private FastDateFormat formatterYearMax;
    private FastDateFormat chatDate;
    private FastDateFormat chatFullDate;

    private Context mContext;

    private FastDateFormat createFormatter(Locale locale, String format, String defaultFormat) {
        if (format == null || format.length() == 0) {
            format = defaultFormat;
        }
        FastDateFormat formatter;
        try {
            formatter = FastDateFormat.getInstance(format, locale);
        } catch (Exception e) {
            format = defaultFormat;
            formatter = FastDateFormat.getInstance(format, locale);
        }
        return formatter;
    }

    /**
     * Init DateUtil.
     *
     * @param context Context
     */
    public void init(Context context) {
        Locale locale = Locale.getDefault();
        boolean is24HourFormat = true;
        mContext = context;
        formatterMonth = createFormatter(locale, mContext.getString(R.string.formatterMonth), "dd MMM");
        formatterYear = createFormatter(locale, mContext.getString(R.string.formatterYear), "dd.MM.yy");
        formatterYearMax = createFormatter(locale, mContext.getString(R.string.formatterYearMax), "dd.MM.yyyy");
        chatDate = createFormatter(locale, mContext.getString(R.string.chatDate), "d MMMM");
        chatFullDate = createFormatter(locale, mContext.getString(R.string.chatFullDate), "d MMMM yyyy");
        formatterWeek = createFormatter(locale, mContext.getString(R.string.formatterWeek), "EEE");
        formatterMonthYear = createFormatter(locale, mContext.getString(R.string.formatterMonthYear), "MMMM yyyy");
        formatterDay = createFormatter(locale, is24HourFormat ? mContext.getString(R.string.formatterDay24H) : mContext.getString(R.string.formatterDay12H), is24HourFormat ? "HH:mm" : "h:mm a");
    }

    /**
     * Format date online string.
     *
     * @param date long milliseconds
     * @return date string
     */
    public String formatDateOnline(long date) {
        try {
            Calendar rightNow = Calendar.getInstance();
            int day = rightNow.get(Calendar.DAY_OF_YEAR);
            int year = rightNow.get(Calendar.YEAR);
            rightNow.setTimeInMillis(date * 1000);
            int dateDay = rightNow.get(Calendar.DAY_OF_YEAR);
            int dateYear = rightNow.get(Calendar.YEAR);

            if (dateDay == day && year == dateYear) {
                return String.format("%s %s %s", mContext.getString(R.string.LastSeen), mContext.getString(R.string.TodayAt), formatterDay.format(new Date(date * 1000)));
            } else if (dateDay + 1 == day && year == dateYear) {
                return String.format("%s %s %s", mContext.getString(R.string.LastSeen), mContext.getString(R.string.YesterdayAt), formatterDay.format(new Date(date * 1000)));
            } else if (year == dateYear) {
                String format = mContext.getString(R.string.formatDateAtTime, formatterMonth.format(new Date(date * 1000)), formatterDay.format(new Date(date * 1000)));
                return String.format("%s %s", mContext.getString(R.string.LastSeenDate), format);
            } else {
                String format = mContext.getString(R.string.formatDateAtTime, formatterYear.format(new Date(date * 1000)), formatterDay.format(new Date(date * 1000)));
                return String.format("%s %s", mContext.getString(R.string.LastSeenDate), format);
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Format date string
     *
     * @param date the date
     * @return the string
     */
    public String formatDateCall(long date) {
        try {
            Calendar rightNow = Calendar.getInstance();
            int day = rightNow.get(Calendar.DAY_OF_YEAR);
            int year = rightNow.get(Calendar.YEAR);
            rightNow.setTimeInMillis(date * 1000);
            int dateDay = rightNow.get(Calendar.DAY_OF_YEAR);
            int dateYear = rightNow.get(Calendar.YEAR);

            if (dateDay == day && year == dateYear) {
                return String.format("%s %s", mContext.getString(R.string.TodayAt), formatterDay.format(new Date(date * 1000)));
            } else if (dateDay + 1 == day && year == dateYear) {
                return String.format("%s %s", mContext.getString(R.string.YesterdayAt), formatterDay.format(new Date(date * 1000)));
            } else if (year == dateYear) {
                return mContext.getString(R.string.formatDateAtTime, formatterMonth.format(new Date(date * 1000)), formatterDay.format(new Date(date * 1000)));
            } else {
                return mContext.getString(R.string.formatDateAtTime, formatterYear.format(new Date(date * 1000)), formatterDay.format(new Date(date * 1000)));
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Format date string
     *
     * @param date the date
     * @return the string
     */
    public String formatDateChat(long date) {
        try {
            Calendar rightNow = Calendar.getInstance();
            int day = rightNow.get(Calendar.DAY_OF_YEAR);
            int year = rightNow.get(Calendar.YEAR);
            rightNow.setTimeInMillis(date);
            int dateDay = rightNow.get(Calendar.DAY_OF_YEAR);
            int dateYear = rightNow.get(Calendar.YEAR);

            if (dateDay == day && year == dateYear) {
                return formatterDay.format(new Date(date));
            } else if (dateDay + 1 == day && year == dateYear) {
                return mContext.getString(R.string.YesterdayAt);
            } else if (year == dateYear) {
                return formatterMonth.format(new Date(date));
            } else {
                return mContext.getString(R.string.formatDateAtTime, formatterYear.format(new Date(date)), formatterDay.format(new Date(date)));
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Format date string.
     *
     * @param date long milliseconds
     * @return date string
     */
    public String formatDate(long date) {
        try {
            Calendar rightNow = Calendar.getInstance();
            int day = rightNow.get(Calendar.DAY_OF_YEAR);
            int year = rightNow.get(Calendar.YEAR);
            rightNow.setTimeInMillis(date);
            int dateDay = rightNow.get(Calendar.DAY_OF_YEAR);
            int dateYear = rightNow.get(Calendar.YEAR);

            if (dateDay == day && year == dateYear) {
                return formatterDay.format(new Date(date));
            } else if (dateDay + 1 == day && year == dateYear) {
                return mContext.getString(R.string.Yesterday);
            } else if (year == dateYear) {
                return formatterMonth.format(new Date(date));
            } else {
                return formatterYear.format(new Date(date));
            }
        } catch (Exception e) {
            return null;
        }
    }
}
