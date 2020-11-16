package com.example.demo.Entity;

import java.util.Date;

public class Theme {
    Integer id;
    String name;
    String background_url;
    String bgm_url;
    Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground_url() {
        return background_url;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }

    public String getBgm_url() {
        return bgm_url;
    }

    public void setBgm_url(String bgm_url) {
        this.bgm_url = bgm_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
