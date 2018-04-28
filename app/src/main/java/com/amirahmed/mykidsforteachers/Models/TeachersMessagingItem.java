package com.amirahmed.mykidsforteachers.Models;



public class TeachersMessagingItem {

    public String teacherName;
    public String teacherJob;

    public TeachersMessagingItem(String teacherName, String teacherJob) {
        this.teacherName = teacherName;
        this.teacherJob = teacherJob;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherJob() {
        return teacherJob;
    }

    public void setTeacherJob(String teacherJob) {
        this.teacherJob = teacherJob;
    }
}
