package com.example.asome.asome_sourcerequire;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RangePicker extends Activity {
    private DateRangeCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_range_picker);


        calendar = (DateRangeCalendarView) findViewById(R.id.calendar);

        //Typeface typeface = Typeface.createFromAsset(getAssets(), "JosefinSans-Regular.ttf");
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "LobsterTwo-Regular.ttf");
      //  calendar.setFonts(typeface);

        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                //Toast.makeText(RangePicker.this, "Start Date: " + startDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                DateFormat df = new SimpleDateFormat("MMMM");
                try {
                    Date date = df.parse("july");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    System.out.print(cal.get(Calendar.MONTH));

                    int selectedMonth = startDate.get(Calendar.MONTH) + 1;
                    int selectedDay = startDate.get(Calendar.DATE) ;
                    Toast.makeText(RangePicker.this, "Start Date: " + String.valueOf(selectedMonth)+"월" +String.valueOf(selectedDay) + "일 End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e){

                }
            }

        });


    }

}

