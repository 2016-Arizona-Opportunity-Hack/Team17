package com.readonchandler.view;

import java.util.ArrayList;

/**
 * Created by Lakshmisagar on 10/1/2016.
 */

public class EventsModel {
    private String title, time;

    public EventsModel() {
    }

    public EventsModel(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
