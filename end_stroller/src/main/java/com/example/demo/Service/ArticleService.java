package com.example.demo.Service;

import com.example.demo.Entity.Drawing;
import com.example.demo.Entity.Result;

import java.util.List;
import java.util.Map;

public class ArticleService extends WorkService{

    /**
     * 获取文章
     * @param req（key/classify/page/state???）
     * @return
     */
    public List browseArticle(Map req){
        /**需要条件获取文章列表**/
        return null;
    }

    /**
     * 获取文章分类
     * @return
     */
    public List getArticleClassify(){
        /** 需要获取所有的文章分类 包括id 和 name**/
        return null;
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    public Drawing getArticleDetail(int id){
        /** 需要id条件查询某条文章的信息**/
        return null;
    }

    /**
     * 点赞文章
     * @param addInfo（id,type）
     * @return
     */
    public Result addLike(Map addInfo){
        return null;
    }

    /**
     * 发布文章信息
     * @param res
     * @return
     */
    public Result PublishArticle(Map res){
        return null;
    }

}
