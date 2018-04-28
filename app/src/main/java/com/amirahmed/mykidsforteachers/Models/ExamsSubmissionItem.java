package com.amirahmed.mykidsforteachers.Models;



public class ExamsSubmissionItem {

    String studentName;
    String studentScore;
    String studentComment;

    public ExamsSubmissionItem(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(String studentScore) {
        this.studentScore = studentScore;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }
}
