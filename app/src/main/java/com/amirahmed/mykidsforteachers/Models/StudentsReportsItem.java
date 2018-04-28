package com.amirahmed.mykidsforteachers.Models;



public class StudentsReportsItem {

    String studentName;
    String input1;
    String input2;

    public StudentsReportsItem(String studentName, String input1, String input2) {
        this.studentName = studentName;
        this.input1 = input1;
        this.input2 = input2;
    }



    public StudentsReportsItem() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInput1() {
        return input1;
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public String getInput2() {
        return input2;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }
}
