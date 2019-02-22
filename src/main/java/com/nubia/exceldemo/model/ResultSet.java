package com.nubia.exceldemo.model;

import java.util.List;

public class ResultSet {
    // 返回页面的状态 1正常 0失败
    private int state;
    private List<Course> courses;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
