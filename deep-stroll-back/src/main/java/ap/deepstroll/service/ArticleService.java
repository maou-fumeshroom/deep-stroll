package ap.deepstroll.service;

import ap.deepstroll.entity.ClassifyArticleEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.ArticleMapper;
import ap.deepstroll.mapper.ClassifyArticleMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.vo.request.ArticleVO;
import ap.deepstroll.vo.request.WorkVO;
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
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassifyArticleMapper classifyArticleMapper;
    @Autowired
    DrawingService drawingService;

//    @Override
//    public Map<String,Object> browseWork(){
//        HashMap<String,Object> response = new HashMap<>();
//        HashMap<String,Object> data = new HashMap<>();
//        try {
//            List<ArticleEntity> articleList =  articleMapper.queryArticleByTitleLabClassifyState(null,null,null,0,null,null,null);
//            data.put("article",articleList);
//            Result result = new Result();
//            Integer totalPage= articleMapper.queryArticleNumByTitleLabClassifyState(null,null,null,0,null)/this.pageSize + 1;
//            data.put("totalpage",totalPage);
//            response.put("result",result);
//            response.put("data",data);
//        }catch (Exception e){
//            Result result = new Result(e.getMessage());
//            response.put("data",null);
//            response.put("result",result);
//        }
//        return response;
//    }

    @Override
    /***
     * lqy
     * 分类浏览用户分享的作品:hao多参数
     */
    public Map<String,Object> searchWork(String title,String label,Integer classifyId,Integer state,Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<ArticleEntity> articleList =  articleMapper.queryArticleByTitleLabClassifyState(title!="" ? title:null,label!="" ? label:null,classifyId,state,startIndex,this.pageSize,null);
            data.put("article",articleList);
            Result result = new Result();
            Integer totalPage= articleMapper.queryArticleNumByTitleLabClassifyState(title!="" ? title:null,label!="" ? label:null,classifyId,state,null)/this.pageSize;
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
     * 获取作品的分类
     * @return
     */
    @Override
    public Map<String,Object> getClassify(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<ClassifyArticleEntity> classifyArticleEntities= classifyArticleMapper.queryAllClassifyArticle(0);
            data.put("classify",classifyArticleEntities);
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("result",new Result(e.getMessage()));
        }
        return response;
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

    @Override
    public Map<String, Result> Publish(WorkVO work, long id) {
        ArticleVO req = (ArticleVO)work;
        HashMap<String,Result> response = new HashMap<>();
        ArticleEntity articleEntity = ArticleEntity.builder().cover(req.getCover())
                                                            .authorId(id)
                                                            .title(req.getTitle())
                                                            .classifyId(req.getClassify())
                                                            .labels(null)
                                                            .introduction(req.getIntroduction())
                                                            .url(req.getFileUrl()).build();
        try{
            articleMapper.insertArticle(articleEntity);
            Result result = new Result();
            response.put("result",result);
            return response;
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
            return response;
        }
    }


    public Result Collection(Map<String,String> req){
        return null;
    }

    /**
     * 删除作品
     * @param id
     * @param type
     * @return
     */
    public Map<String,Result> deleteWork(Long id, Integer type) {
        if (type == 1) return drawingService.deleteWork(id, type);
        Map<String,Result>response = new HashMap<>();
        try{
            articleMapper.updateArticleState(id,1);
            Result result = new Result();
            response.put("result",result);
            return response;
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
            return response;
        }
    }

}
