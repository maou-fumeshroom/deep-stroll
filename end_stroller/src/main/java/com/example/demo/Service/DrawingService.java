package com.example.demo.Service;

import com.example.demo.Entity.Drawing;
import com.example.demo.Entity.Result;

import java.util.List;
import java.util.Map;

public class DrawingService extends WorkService{


    /**
     * 获取手绘
     * @param req（key/classify/page/state???）
     * @return
     */
    public List browseDrawing(Map req){
        /**需要条件获取手绘列表**/
        return null;
    }

    /**
     * 获取手绘分类
     * @return
     */
    public List getDrawingClassify(){
        /** 需要获取所有的手绘分类 包括id 和 name**/
        return null;
    }

    /**
     * 获取手绘详情
     * @param id
     * @return
     */
    public Drawing getDrawingDetail(int id){
        /** 需要id条件查询某条手绘的信息**/
        return null;
    }

    /**
     * 发布手绘信息
     * @param res
     * @return
     */
    public Result PublishDrawing(Map res){
        return null;
    }




}
