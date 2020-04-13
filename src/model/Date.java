package model;

import java.util.Calendar;

public class Date {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public Date() {
    }

    public Date(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    long getDueTimeInSecond() {
        return (long) (((((year * 12) + month) * 30 + day) * 24 + hour) * 60 + minute) * 60 + second;
    }

    public static int getSystemYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getSystemMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getSystemDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getSystemHour() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }

    public static int getSystemMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getSystemSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static long getSystemTimeInSecond() {
        return (long) (((((getSystemYear() * 12) + getSystemMonth()) * 30 + getSystemDay()) * 24 + getSystemHour()) * 60 + getSystemMinute()) * 60 + getSystemSecond();
    }

    public long getRemainingTimeInSecond() {
        return getDueTimeInSecond() - getSystemTimeInSecond();
    }

    @Override
    public String toString() {
        return year +
                "/" + month +
                "/" + day +
                " " + hour +
                ":" + minute +
                ":" + second;
    }

    public static String systemToString() {
        return getSystemYear() +
                "/" + getSystemMonth() +
                "/" + getSystemDay() +
                " " + getSystemHour() +
                ":" + getSystemMinute() +
                ":" + getSystemSecond();
    }


}
