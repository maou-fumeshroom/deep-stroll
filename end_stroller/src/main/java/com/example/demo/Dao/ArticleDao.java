package com.example.demo.Dao;

import com.example.demo.Entity.Drawing;

import java.util.List;
import java.util.Map;

public class ArticleDao {


    /**
     * 添加文章
     */
    public boolean addArticle(Map info){
        return false;
    }

    /** 修改文章状态**/
    public boolean ChangeArticleStatus(int id){
        return false;
    }

    /**
     * 获取所有的文章分类
     * @return
     */
    public List getAllArticleClassify(){
        return null;
    }

    /**
     * 获取id对应的文章信息
     * @param id
     * @return
     */
    public Drawing getArticleById(int id){
        return null;
    }

    /**
     *根据classify page 条件查询合适的文章信息
     * @param req
     * @return
     */
    public List getArticleByClassify_page(Map req){
        return null;
    }

    /**
     * 根据 key page 条件查询合适的文章信息
     */
    public List getArticleByConditionKey_page(Map req){
        return null;
    }

    /**
     * 根据指定的点赞数获取热门文章
     */
    public List getHotArticle(int likeNum){
        return null;
    }
}
