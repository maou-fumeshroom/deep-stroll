package com.example.demo.Entity;

import java.util.Date;

public class ClassifyDrawing {
    Integer classify_drawing_id;
    String name;
    Integer state = 0;
    Date create_time;
    Date update_time;

    public Integer getClassify_drawing_id() {
        return classify_drawing_id;
    }

    public void setClassify_drawing_id(Integer classify_drawing_id) {
        this.classify_drawing_id = classify_drawing_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
