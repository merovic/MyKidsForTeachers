package com.amirahmed.mykidsforteachers.Models;

public class SmartClassItem {

    String className;
    String classClass;

    public SmartClassItem(String className, String classClass) {
        this.className = className;
        this.classClass = classClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassClass() {
        return classClass;
    }

    public void setClassClass(String classClass) {
        this.classClass = classClass;
    }
}
