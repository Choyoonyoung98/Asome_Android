package com.example.asome.asome_sourcerequire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.asome.asome_sourcerequire.Project.Role;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class CalendarActivity extends AppCompatActivity {

    private CustomCalendar customCalendar;
    ArrayList<Role> arr_role;
    List<String> allDatesString;
    int member_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        customCalendar = (CustomCalendar) findViewById(R.id.customCalendar);


     /* Intent intent = getIntent();
        arr_role = (ArrayList<Role>) intent.getSerializableExtra("arr_role");
        Log.e("calendar", String.valueOf(arr_role.size()));

        String[] arr_date = {"2018-08-10", "2018-08-11", "2018-08-12", "2018-09-16", "2018-08-25"};


        //날짜 레인지
        for (int i = 0; i < arr_role.size(); i++) {
            member_id=i;
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = arr_role.get(i).getRole_start_date();
            String date2 = arr_role.get(i).getRole_end_date();
            try {
                Date d1 = myFormat.parse(date1);
                Date d2 = myFormat.parse(date2);
                List<Date> allDates = new ArrayList<Date>();//no
                allDatesString = new ArrayList<String>();
                while (d1.before(d2)) {
                    d1 = addDays(d1, 1);
                    allDates.add(d1);
                    allDatesString.add(formatter.format(d1));
                }
                insertDate(allDatesString);
                // System.out.println("date44 " + allDates);
                System.out.println("date44 " + allDatesString);
                System.out.println("date44of size  " + allDatesString.size());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


    }
    int eventCount = 1;
    public void insertDate(List<String> dates) {
        for (int i = 0; i < dates.size(); i++) {

            // int index_role = i;
    *//*        if(i<3)
                eventCount=i+1;*//*
            customCalendar.addAnEvent(dates.get(i), eventCount, getEventDataList(eventCount, member_id));

        }
    }
    EventData dateData;
    ArrayList<dataAboutDate> dataAboutDates;
    public ArrayList<EventData> getEventDataList(int count, int index_role) {
        ArrayList<EventData> eventDataList = new ArrayList();

        // for (int i = 0; i < count; i++) {
         dateData = new EventData();
     dataAboutDates = new ArrayList();
//name
    dateData.setSection(arr_role.get(index_role).getRole_name());
        dataAboutDate dataAboutDate = new dataAboutDate();

       // int index = new Random().nextInt(CalendarUtils.getEVENTS().length);

        dataAboutDate.setTitle(arr_role.get(index_role).getRole_name());
        dataAboutDate.setSubject("디테일");
        dataAboutDates.add(dataAboutDate);

        dateData.setData(dataAboutDates);
        eventDataList.add(dateData);
        //    }

        return eventDataList;
    }

    private static Date addDays(Date d1, int i) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d1);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

*/


        String[] arr = {"2018-09-10", "2018-09-11", "2018-09-15", "2018-09-16", "2018-09-25"};
        for (int i = 0; i < 5; i++) {
            int eventCount = 3;
            customCalendar.addAnEvent(arr[i], eventCount, getEventDataList(eventCount));
        }
    }

    public ArrayList<EventData> getEventDataList(int count) {
        ArrayList<EventData> eventDataList = new ArrayList();

        for (int i = 0; i < count; i++) {
            EventData dateData = new EventData();
            ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

            dateData.setSection(CalendarUtils.getNAMES()[new Random().nextInt(CalendarUtils.getNAMES().length)]);
            dataAboutDate dataAboutDate = new dataAboutDate();

            int index = new Random().nextInt(CalendarUtils.getEVENTS().length);

            dataAboutDate.setTitle(CalendarUtils.getEVENTS()[index]);
            dataAboutDate.setSubject(CalendarUtils.getEventsDescription()[index]);
            dataAboutDates.add(dataAboutDate);

            dateData.setData(dataAboutDates);
            eventDataList.add(dateData);
        }

        return eventDataList;
    }
}