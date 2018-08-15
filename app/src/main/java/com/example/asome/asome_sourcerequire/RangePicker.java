package com.example.asome.asome_sourcerequire;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RangePicker extends Dialog {
    private DateRangeCalendarView calendar;
    Context context;

    public RangePicker(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

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
               // DateFormat df = new SimpleDateFormat("MMMM");
                try {
                   // Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    //cal.setTime(date);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(cal.getTime());
// Output "Wed Sep 26 14:23:28 EST 2012"

                    String formatted = format1.format(startDate.getTime());
                    String formatted2 = format1.format(endDate.getTime());
                    System.out.println(formatted);
                    //           System.out.print(cal.get(Calendar.MONTH));
                   // String s = df.format(cal);
                    int selectedMonth = startDate.get(Calendar.MONTH) + 1;
                    int selectedDay = startDate.get(Calendar.DATE);
                    //      Toast.makeText(context, "Start Date: " + String.valueOf(selectedMonth) + "월" + String.valueOf(selectedDay) + "일 End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();
                 //   Log.e("RangePicker", "Start Date: " + String.valueOf(selectedMonth) + "월" + String.valueOf(selectedDay) + "일 End date: " + endDate.getTime().toString());
                    Log.e("RangePicker", "쓸것"+formatted+formatted2);
                } catch (Exception e) {
                    Log.e("RangePicker", String.valueOf(e.getStackTrace()));
                    e.printStackTrace();
                    Toast.makeText(context,
                            e.toString(), Toast.LENGTH_LONG).show();

                }
            }

        });


    }

}

