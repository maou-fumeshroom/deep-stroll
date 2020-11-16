package com.example.demo.Dao;

import com.example.demo.Entity.Theme;

import java.util.List;

public class ThemeDao {


    /**
     * 查询主题列表
     */
    public List getThemeList(){
        return null;
    }

    /**
     * Id获取主题信息
     */
    public Theme getThemeById(Integer themeId){
        return null;
    }

    /**
     * Id删除主题信息
     */
    public boolean deleteThemeById(Integer themeId){
        return false;
    }

    /**
     * 添加网页主题可选项:可以只添加音乐吗？
     */
    public boolean addNewTheme(String name,String background_url,String bgm_url){ return false; }

}
