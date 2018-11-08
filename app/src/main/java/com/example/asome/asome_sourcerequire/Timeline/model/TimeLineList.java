package com.example.asome.asome_sourcerequire.Timeline.model;

import java.util.List;

public class TimeLineList  {

    private List<TimeLine> timeLine;

    public TimeLineList(List<TimeLine> timeLine) {
        this.timeLine = timeLine;
    }

    public List<TimeLine> getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(List<TimeLine> timeLine) {
        this.timeLine = timeLine;
    }
}
