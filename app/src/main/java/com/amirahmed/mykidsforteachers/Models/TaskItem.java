package com.amirahmed.mykidsforteachers.Models;

public class TaskItem {

    String title;
    String date;
    String time;
    String type;

    public TaskItem(String title, String date, String time, String type) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
