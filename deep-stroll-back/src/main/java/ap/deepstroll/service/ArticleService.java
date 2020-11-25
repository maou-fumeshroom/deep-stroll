package ap.deepstroll.service;

import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap.deepstroll.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleService extends WorkService{
    @Autowired
    ArticleMapper articleMapper;

    @Override
    /***
     * lqy
     * 分类浏览用户分享的作品:这么多参数吗
     */
    public List<ArticleEntity> browseWork(String title,String label,Integer classifyId,Integer state,Integer likeNum,Integer startIndex,Integer pageSize) {
        return articleMapper.queryArticleByTitleLabState(title,label,classifyId,state,startIndex,pageSize,likeNum);
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

    @Override
    public Integer Publish(Work work) {
        return null;
    }

    /***
     * lqy:how to
     * @param articleEntity
     * @return
     */
    public Integer Publish(ArticleEntity articleEntity) {
        return articleMapper.insertArticle(articleEntity);
    }

}
