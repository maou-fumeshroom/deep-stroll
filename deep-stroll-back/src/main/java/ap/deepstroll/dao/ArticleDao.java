package ap.deepstroll.dao;


import java.util.List;
import java.util.Map;

import ap.deepstroll.entity.ArticleEntity;

public class ArticleDao {

    //DONE
    /**
     * 添加文章
     */
    public boolean addArticle(Map info){
        return false;
    }

   //DONE
    /**
     * 获取所有的文章分类
     * @return
     */
    public List getAllArticleClassify(){
        return null;
    }

    //DONE
    /**
     * 获取id对应的文章信息
     * @param id
     * @return
     */
    public ArticleEntity getArticleById(Integer id){
        return null;
    }

    //DONE
    /**
     *根据classify page 条件查询合适的文章信息
     * @param req
     * @return
     */
    public List getArticleByClassifyPage(Map req){
        return null;
    }

    //DONE
    /**
     * 根据 key page 条件查询合适的文章信息
     */
    public List getArticleByConditionKeyPage(Map req){
        return null;
    }

    //DONE
    /**
     * 根据指定的点赞数获取热门文章
     */
    public List getHotArticle(Integer likeNum){
        return null;
    }

    //TODO
    /**
     * 修改分类
     */
    public boolean changeArticleClassify(Integer id){
        return false;
    }

    //DONE
     /** 修改文章状态**/
     public boolean ChangeArticleStatus(Integer id){
        return false;
    }

}
