package ap.deepstroll.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ap.deepstroll.bo.ClassifyBO;
import ap.deepstroll.bo.DetailAuthorBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.SearchArticalBO;
import ap.deepstroll.bo.MyArticleBO;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.ClassifyArticleEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.ArticleMapper;
import ap.deepstroll.mapper.ClassifyArticleMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.utils.DateFormatUtil;
import ap.deepstroll.vo.request.ArticleVO;
import ap.deepstroll.vo.request.WorkVO;
import ap.deepstroll.vo.response.GetDetailArticleVO;
import ap.deepstroll.vo.response.MyWorksArticleVO;
import ap.deepstroll.vo.response.ResponseVO;
import ap.deepstroll.vo.response.SearchWorkArticleVO;

@Service("articleService")
@Primary
public class ArticleService extends WorkService {
    Integer pageSize = 10;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassifyArticleMapper classifyArticleMapper;
    @Autowired
    DrawingService drawingService;

    // @Override
    // public Map<String,Object> browseWork(){
    // HashMap<String,Object> response = new HashMap<>();
    // HashMap<String,Object> data = new HashMap<>();
    // try {
    // List<ArticleEntity> articleList =
    // articleMapper.queryArticleByTitleLabClassifyState(null,null,null,0,null,null,null);
    // data.put("article",articleList);
    // Result result = new Result();
    // Integer totalPage=
    // articleMapper.queryArticleNumByTitleLabClassifyState(null,null,null,0,null)/this.pageSize
    // + 1;
    // data.put("totalpage",totalPage);
    // response.put("result",result);
    // response.put("data",data);
    // }catch (Exception e){
    // Result result = new Result(e.getMessage());
    // response.put("data",null);
    // response.put("result",result);
    // }
    // return response;
    // }

    private String transNull(String s) {
        return  s == "" ? null:s;
    }

    @Override
    public ResponseVO searchWork(String title,String labels,Integer classifyId,Integer state,Integer page) {
        title = transNull(title);
        labels = transNull(labels);
        try {
            List<ArticleEntity> articleEntities = articleMapper.queryArticleByTitleLabClassifyState(
                                                                title, 
                                                                labels, 
                                                                classifyId, 
                                                                state, 
                                                                (page - 1) * pageSize, 
                                                                pageSize, 
                                                                null);
            List<SearchArticalBO> articalBOs = new ArrayList<SearchArticalBO>();

            for (ArticleEntity articleEntity: articleEntities) {
                UserEntity author = userMapper.queryUserById(articleEntity.getAuthorId());
                String classifyName = classifyArticleMapper.queryClassifyById(articleEntity.getClassifyId()).getName();
                articalBOs.add(
                    SearchArticalBO.builder()
                                   .title(articleEntity.getTitle())
                                   .id(articleEntity.getId())
                                   .cover(articleEntity.getCover())
                                   .introduction(articleEntity.getIntroduction())
                                   .avatar(author.getAvatar())
                                   .nickname(author.getNickname())
                                   .dateTime(DateFormatUtil.getDate(articleEntity.getCreateTime()))
                                   .likeNum(0)
                                   .comment(0)
                                   .status(articleEntity.getState())
                                   .classifyName(classifyName)
                                   .build()
                );
            }
            
            Integer totalLine = articleMapper.queryArticleNumByTitleLabClassifyState(
                                              title, 
                                              labels, 
                                              classifyId, 
                                              state, 
                                              null);
            Integer totalPage = totalLine;
            SearchWorkArticleVO vo = SearchWorkArticleVO.builder()
                                                        .articles(articalBOs.toArray(new SearchArticalBO[articalBOs.size()]))
                                                        .totalPage(totalPage)
                                                        .build();
            return ResponseVO.builder()
                             .data(vo)
                             .result(new Result())
                             .build();
        } catch(Exception e) {
            return ResponseVO.builder()
                             .result(new Result("server error"))
                             .build();
        }
    }

    @Override
    public ResponseVO getDetail(Long id) {
        try {
            ArticleEntity articleEntity = articleMapper.queryArticleById(id);
            if (articleEntity == null) {
                return ResponseVO.builder()
                             .result(new Result("文章已删除或不存在"))
                             .build();
            }
            UserEntity authorEntity = userMapper.queryUserById(articleEntity.getAuthorId());
            String classifyName = classifyArticleMapper.queryClassifyById(articleEntity.getClassifyId()).getName();
            DetailAuthorBO author = DetailAuthorBO.builder()
                                                  .id(authorEntity.getId())
                                                  .nickname(authorEntity.getNickname())
                                                  .avatar(authorEntity.getAvatar())
                                                  .build();
            GetDetailArticleVO vo = GetDetailArticleVO.builder()
                                                      .author(author)
                                                      .title(articleEntity.getTitle())
                                                      .cover(articleEntity.getCover())
                                                      .introduction(articleEntity.getIntroduction())
                                                      .fileUrl(articleEntity.getUrl())
                                                      .likeNum(0)
                                                      .isLike(0)
                                                      .isCollect(0)
                                                      .labels(null)
                                                      .dateTime(DateFormatUtil.getDate(articleEntity.getCreateTime()))
                                                      .status(articleEntity.getState())
                                                      .classifyName(classifyName)
                                                      .type(0)
                                                      .build();
            return ResponseVO.builder()
                             .data(vo)
                             .result(new Result())
                             .build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseVO.builder()
                             .result(new Result("server error"))
                             .build();
        }
    }

    public ResponseVO myWorks(Long id, String title, String labels, Integer classifyId, Integer page) {
        title = transNull(title);
        labels = transNull(labels);
        try {
            List<ArticleEntity> articleEntities = articleMapper.queryArticleByAuthorId(id, title, labels, classifyId, null, (page - 1) * pageSize, pageSize);
            List<MyArticleBO> articalBOs = new ArrayList<MyArticleBO>();

            for (ArticleEntity articleEntity: articleEntities) {
                articalBOs.add(
                    MyArticleBO.builder()
                               .id(articleEntity.getId())
                               .cover(articleEntity.getCover())
                               .title(articleEntity.getTitle())
                               .introduction(articleEntity.getIntroduction())
                               .dateTime(DateFormatUtil.getDate(articleEntity.getCreateTime()))
                               .likeNum(0)
                               .comment(0)
                               .status(articleEntity.getState())
                               .build()

                );
            }
            
            Integer totalLine = articleMapper.queryArticleNumByAuthorId(id, title, labels, classifyId, null);
            Integer totalPage = totalLine;
            MyWorksArticleVO vo = MyWorksArticleVO.builder()
                                                  .articles(articalBOs.toArray(new MyArticleBO[articalBOs.size()]))
                                                  .totalPage(totalPage)
                                                  .build();
            return ResponseVO.builder()
                             .data(vo)
                             .result(new Result())
                             .build();
        } catch(Exception e) {
            return ResponseVO.builder()
                             .result(new Result("server error"))
                             .build();
        }
    }

    /***
     * lqy 获取作品的分类
     * 
     * @return
     */
    @Override
    public Map<String, Object> getClassify() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<ClassifyArticleEntity> classifyArticleEntities = classifyArticleMapper.queryAllClassifyArticle(0);
            List<ClassifyBO> classifys = new ArrayList<ClassifyBO>();
            for (ClassifyArticleEntity c : classifyArticleEntities) {
                classifys.add(ClassifyBO.builder().id(c.getId()).name(c.getName()).build());
            }
            data.put("classifys", classifys);
            response.put("data", data);
            response.put("result", new Result());
        } catch (Exception e) {
            response.put("result", new Result(e.getMessage()));
        }
        return response;
    }

    @Override
    public Map<String, Result> Publish(WorkVO work, long id) {
        ArticleVO req = (ArticleVO) work;
        HashMap<String, Result> response = new HashMap<>();
        ArticleEntity articleEntity = ArticleEntity.builder().cover(req.getCover() == "" ? null : req.getCover())
                .authorId(id).title(req.getTitle()).classifyId(req.getClassify()).labels("")
                .introduction(req.getIntroduction()).url(req.getFileUrl()).build();
        try {
            articleMapper.insertArticle(articleEntity);
            Result result = new Result();
            response.put("result", result);
            return response;
        } catch (Exception e) {
            Result result = new Result(e.getMessage());
            response.put("result", result);
            return response;
        }
    }

    public Result Collection(Map<String, String> req) {
        return null;
    }

    /**
     * 删除作品
     * 
     * @param id
     * @param type
     * @return
     */
    public Map<String, Result> deleteWork(Long id, Integer type) {
        if (type == 1)
            return drawingService.deleteWork(id, type);
        Map<String, Result> response = new HashMap<>();
        try {
            //articleMapper.updateArticleState(id, 1);
            articleMapper.realDelete(id);
            Result result = new Result();
            response.put("result", result);
            return response;
        } catch (Exception e) {
            Result result = new Result(e.getMessage());
            response.put("result", result);
            return response;
        }
    }

}
