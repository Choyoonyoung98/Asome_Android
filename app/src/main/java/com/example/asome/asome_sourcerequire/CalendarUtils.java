package com.example.asome.asome_sourcerequire;

import java.text.SimpleDateFormat;

public class CalendarUtils {
    private static final String TAG = com.riontech.calendar.utils.CalendarUtils.class.getSimpleName();
    private static final String CALENDAR_DB_FORMAT = "yyyy-MM-dd";
    private static final String CALENDAR_DATE_FORMAT = "MMM dd yyyy";
    private static final String CALENDAR_MONTH_TITLE_FORMAT = "MMMM yyyy";
    private static final String[] NAMES = {"윤영", "경연", "은선", "연지"};
    private static final String[] EVENTS = {"Task", "Task", "Task"};
    private static final String[] EVENTS_DESCRIPTION = {"프레젠테이션 준비", "클라이언트 미팅"
            , "개발작업 스케쥴 조정"};

    public static SimpleDateFormat getCalendarDBFormat() {
        return new SimpleDateFormat(CALENDAR_DB_FORMAT);
    }

    public static SimpleDateFormat getCalendarDateFormat() {
        return new SimpleDateFormat(CALENDAR_DATE_FORMAT);
    }

    public static String getCalendarMonthTitleFormat(){
        return CALENDAR_MONTH_TITLE_FORMAT;
    }

    public static String[] getNAMES() {
        return NAMES;
    }

    public static String[] getEVENTS() {
        return EVENTS;
    }

    public static String[] getEventsDescription() {
        return EVENTS_DESCRIPTION;
    }
}