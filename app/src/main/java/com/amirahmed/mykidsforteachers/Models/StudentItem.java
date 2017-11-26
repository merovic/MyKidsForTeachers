package com.amirahmed.mykidsforteachers.Models;



public class StudentItem {

    public int photoId;
    public String name;

    public StudentItem(int photoId, String name) {
        this.photoId = photoId;
        this.name = name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
