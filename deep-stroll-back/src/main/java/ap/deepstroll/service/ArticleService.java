package ap.deepstroll.service;

import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.ArticleMapper;
import ap.deepstroll.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import ap.deepstroll.bo.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ap.deepstroll.entity.ArticleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("articleService")
@Primary
public class ArticleService extends WorkService{
    Integer pageSize = 10;
    @Autowired
    ArticleMapper articleMapper;
    UserMapper userMapper;

    @Override
    public Map<String,Object> browseWork(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<ArticleEntity> articleList =  articleMapper.queryArticleByTitleLabState(null,null,null,0,null,null,null);
            data.put("article",articleList);
            Result result = new Result();
            Integer totalPage= articleMapper.queryArticleNumByTitleLabState(null,null,null,0,null)/this.pageSize;
            data.put("totalpage",totalPage);
            response.put("result",result);
            response.put("data",data);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("data",null);
            response.put("result",result);
        }
        return response;
    }

    @Override
    /***
     * lqy
     * 分类浏览用户分享的作品:这么多参数吗
     */
    public Map<String,Object> searchWork(String title,String label,Integer classifyId,Integer state,Integer likeNum,Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<ArticleEntity> articleList =  articleMapper.queryArticleByTitleLabState(title,label,classifyId,state,startIndex,this.pageSize,likeNum);
            data.put("article",articleList);
            Result result = new Result();
            Integer totalPage= articleMapper.queryArticleNumByTitleLabState(title,label,classifyId,state,likeNum)/this.pageSize;
            data.put("totalpage",totalPage);
            response.put("result",result);
            response.put("data",data);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("data",null);
            response.put("result",result);
        }
        return response;
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
    public Map<String, Object> getDetail(Long id) {
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            ArticleEntity articleDetial = articleMapper.queryArticleById(id);
            Long authorId = articleDetial.getAuthorId();
            UserEntity userInfo = userMapper.queryUserById(authorId);
            HashMap<String,Object> author = new HashMap<>();
            author.put("id",userInfo.getId());
            author.put("nickname",userInfo.getNickname());
            author.put("avatar",userInfo.getAvatar());
            Result result = new Result();
            data.put("author",author);
            data.put("",userInfo);
            response.put("result",result);
            response.put("data",data);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("data",null);
            response.put("result",result);
        }
        return response;
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
