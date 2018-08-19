package com.example.asome.asome_sourcerequire.Chatting.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.Availability;
import com.anychart.anychart.AvailabilityPeriod;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Resource;
import com.anychart.anychart.TimeTrackingMode;
import com.example.asome.asome_sourcerequire.R;

import java.util.ArrayList;
import java.util.List;

public class TeamScheduleActivity extends AppCompatActivity {


    String name, startDate, endDate;
    List<DataEntry> data;
    Resource resource;
    AnyChartView anyChartView;

    TextView textStatus;
    ImageView statusImage;
    String getStatus="doing";
    String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_schedule);

        statusImage = findViewById(R.id.statusImage);
        textStatus = findViewById(R.id.textStatus);
        Intent intent = getIntent();
        getStatus = intent.getExtras().getString("status","");
        if(getStatus.length() >1)
        {
            if(getStatus.equals("err")) {
                statusImage.setImageResource(R.drawable.err);
                textStatus.setText(getStatus);
            }
            if(getStatus.equals("doing")){
                statusImage.setImageResource(R.drawable.doing);
                textStatus.setText(getStatus);
            }
        }




        anyChartView = findViewById(R.id.any_chart_view);

        resource = AnyChart.resource();

        resource.setZoomLevel(1d)
                .setTimeTrackingMode(TimeTrackingMode.ACTIVITY_PER_CHART)
                .setCurrentStartDate("2018-07-31");

        resource.setResourceListWidth(100);//120

        resource.getCalendar().setAvailabilities(new Availability[]{
                new Availability(AvailabilityPeriod.DAY, (Double) null, 10d, (Double) null, (Double) null, 18d, true),
                new Availability(AvailabilityPeriod.DAY, (Double) null, 14d, (Double) null, (Double) null, 15d, false),
                new Availability(AvailabilityPeriod.WEEK, (Double) null, (Double) null, 5d, (Double) null, 18d, false),
                new Availability(AvailabilityPeriod.WEEK, (Double) null, (Double) null, 6d, (Double) null, 18d, false)
        });

        data = new ArrayList<>();

        data.add(new ResourceDataEntry(
                "황은선",
                "Developer",
                "http://206.189.84.207/asome/image/circle_blue.png",
                new Activity[]{
                        new Activity(
                                "업무 일정 정보",
                                new Interval[]{
                                        new Interval(
                                                "2016-10-01", "2016-10-11", 120)
                                },
                                "#62BEC1")
                }));
        data.add(new ResourceDataEntry(
                "이경연",
                "Developer",
                "http://206.189.84.207/asome/image/err.png",
                new Activity[]{
                        new Activity(
                                "업무 일정 정보",
                                new Interval[]{
                                        new Interval(
                                                "2016-10-01", "2016-10-20", 120)
                                },
                                "#8789C0")
                }));
        data.add(new ResourceDataEntry(
                "김연지",
                "Developer",
                "http://cdn.anychart.com/images/resource-chart/developer-sergio.png",
                new Activity[]{
                        new Activity(
                                "업무 일정 정보",
                                new Interval[]{
                                        new Interval(
                                                "2016-08-12", "2016-10-11", 120)
                                },
                                "#62BEC1")
                }));
        data.add(new ResourceDataEntry(
                "조윤영",
                "Developer",
                "http://cdn.anychart.com/images/resource-chart/developer-sergio.png",
                new Activity[]{
                        new Activity(
                                "업무 일정 정보",
                                new Interval[]{
                                        new Interval(
                                                "2016-05-12", "2016-07-30", 120)
                                },
                                "#8789C0")
                }));


        resource.setData(data);

        anyChartView.setChart(resource);
    }


    public static class ResourceDataEntry extends DataEntry {
        public ResourceDataEntry(String name, String description, String image,TeamScheduleActivity.Activity[] activities) {
            setValue("name", name);
            setValue("description", description);
            setValue("image", image);
            setValue("activities", activities);
        }
    }

    public static class Activity extends DataEntry {
        public Activity(String name, TeamScheduleActivity.Interval[] intervals, String fill) {
            setValue("name", name);
            setValue("intervals", intervals);
            setValue("fill", fill);
        }


    }
    public static class Interval extends DataEntry {
        public Interval(String start, String end, Integer minutesPerDay) {
            setValue("start", start);
            setValue("end", end);
            setValue("minutesPerDay", minutesPerDay);
        }
    }



    @Override
    protected void onRestart() {
        super.onRestart();


    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        startDate = intent.getStringExtra("start_date");
        endDate = intent.getStringExtra("end_date");
        if (name != null && startDate != null && endDate != null) {
            Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

            data.add(new ResourceChartActivity.ResourceDataEntry(
                    name,
                    "Developer",
                    "http://cdn.anychart.com/images/resource-chart/developer-romario.png",
                    new ResourceChartActivity.Activity[]{
                            new ResourceChartActivity.Activity(
                                    "Gantt timeline",
                                    new ResourceChartActivity.Interval[]{
                                            new ResourceChartActivity.Interval(startDate, endDate, 60)
                                    },
                                    "#62BEC1"),

                    }));
            resource.setData(data);

            anyChartView.setChart(resource);

        }

    }
    */
}
