package com.example.demo.Dao;

import com.example.demo.Entity.Drawing;

import java.util.List;
import java.util.Map;

public class DrawingDao {


    /**
     * 添加手绘
     */
    public boolean addDrawing(Map info){
        return false;
    }

    /**
     * 修改手绘状态
     **/
    public boolean ChangeDrawingStatus(int id){
        return false;
    }

    /**
     * 获取所有的手绘分类
     */
    public List getAllDrawingClassify(){
        return null;
    }

    /**
     * 获取id对应的手绘信息
     */
    public Drawing getDrawingById(int id){
        return null;
    }

    /**
     *根据classify page 条件查询合适的手绘信息
     */
    public List getDrawingByClassify_page(Map req){
        return null;
    }

    /**
     * 根据 key page 条件查询合适的手绘信息
     */
    public List getDrawingByConditionKey_page(Map req){
        return null;
    }

    /**
     * 根据指定的点赞数获取热门手绘
     */
    public List getHotDrawing(int likeNum){
        return null;
    }
}
