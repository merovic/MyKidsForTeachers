package com.amirahmed.mykidsforteachers.Models;



public class ExamsMonthItem {

    public String level;
    public String classroom;
    public String date;
    public String subject;

    public ExamsMonthItem(String level, String classroom, String date, String subject) {
        this.level = level;
        this.classroom = classroom;
        this.date = date;
        this.subject = subject;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
