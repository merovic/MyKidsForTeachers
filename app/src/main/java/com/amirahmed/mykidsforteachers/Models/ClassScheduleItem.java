package com.amirahmed.mykidsforteachers.Models;



public class ClassScheduleItem {

    String className;
    String classRoom;
    String from;
    String to;

    public ClassScheduleItem(String className, String classRoom, String from, String to) {
        this.className = className;
        this.classRoom = classRoom;
        this.from = from;
        this.to = to;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
