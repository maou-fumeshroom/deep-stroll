package ap.deepstroll.service;

import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ap.deepstroll.bo.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ap.deepstroll.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

@Service("articleService")
@Primary
public class ArticleService extends WorkService{
    Integer pageSize = 10;
    @Autowired
    ArticleMapper articleMapper;


    @Override
    //result!!!
    public List<ArticleEntity> browseWork(){
        return articleMapper.queryArticleByTitleLabState(null,null,null,0,null,null,null);
    }

    @Override
    /***
     * lqy
     * 分类浏览用户分享的作品:这么多参数吗
     */
    public List<ArticleEntity> searchWork(String title,String label,Integer classifyId,Integer state,Integer likeNum,Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        return articleMapper.queryArticleByTitleLabState(title,label,classifyId,state,startIndex,this.pageSize,likeNum);
    }

    /***
     * lqy
     * 获取作品的分类??这啥我又忘了
     * @return
     */
    @Override
    public List getClassify() {
        return null;
    }

    /***
     * lqy
     * @param id
     * @return
     */
    @Override
    public ArticleEntity getDetail(Long id) {
        return articleMapper.queryArticleById(id);
    }

    /***
     * lqy:how to
     * @param articleEntity
     * @return
     */
    public Integer Publish(ArticleEntity articleEntity) {
        return articleMapper.insertArticle(articleEntity);
    }

    public Result Collection(Map<String,String> req){
        return null;
    }
}
