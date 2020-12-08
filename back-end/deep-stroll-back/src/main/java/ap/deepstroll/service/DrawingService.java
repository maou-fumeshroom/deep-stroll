package ap.deepstroll.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.ClassifyBO;
import ap.deepstroll.bo.DetailAuthorBO;
import ap.deepstroll.bo.MyDrawingBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.SearchDrawingBO;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.*;
import ap.deepstroll.mapper.ClassifyDrawingMapper;
import ap.deepstroll.mapper.DrawingMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.utils.DateFormatUtil;
import ap.deepstroll.vo.request.DrawingVO;
import ap.deepstroll.vo.request.WorkVO;
import ap.deepstroll.vo.response.GetDetailDrawingVO;
import ap.deepstroll.vo.response.ResponseVO;
import ap.deepstroll.vo.response.SearchWorkDrawingVO;
import ap.deepstroll.vo.response.MyWorksDrawingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("drawingService")
public class DrawingService extends WorkService {
    Integer pageSize = 10;
    @Autowired
    DrawingMapper drawingMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassifyDrawingMapper classifyDrawingMapper;

    // @Override
    // public Map<String,Object> browseWork(){
    // HashMap<String,Object> response = new HashMap<>();
    // HashMap<String,Object> data = new HashMap<>();
    // try {
    // List<DrawingEntity> drawingList =
    // drawingMapper.queryDrawingByTitleLabClassifyState(null,null,null,0,0,10,null);
    // data.put("drawing",drawingList);
    // Result result = new Result();
    // Integer totalPage=
    // drawingMapper.queryDrawingNumByTitleLabClassifyState(null,null,null,0,null)/this.pageSize
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
            List<DrawingEntity> darwingEntities = drawingMapper.queryDrawingByTitleLabClassifyState(
                                                                title, 
                                                                labels, 
                                                                classifyId, 
                                                                state, 
                                                                (page - 1) * pageSize, 
                                                                pageSize, 
                                                                null);
            List<SearchDrawingBO> drawingBOs = new ArrayList<SearchDrawingBO>();

            for (DrawingEntity drawingEntity: darwingEntities) {
                UserEntity author = userMapper.queryUserById(drawingEntity.getAuthorId());
                String classifyName = classifyDrawingMapper.queryClassifyById(drawingEntity.getClassifyId()).getName();
                String cover = drawingEntity.getUrl().split(", ")[0];
                drawingBOs.add(
                    SearchDrawingBO.builder()
                                   .title(drawingEntity.getTitle())
                                   .id(drawingEntity.getId())
                                   .cover(cover)
                                   .introduction(drawingEntity.getIntroduction())
                                   .avatar(author.getAvatar())
                                   .nickname(author.getNickname())
                                   .dateTime(DateFormatUtil.getDate(drawingEntity.getCreateTime()))
                                   .likeNum(0)
                                   .comment(0)
                                   .status(drawingEntity.getState())
                                   .classifyName(classifyName)
                                   .build()
                );
            }
            
            Integer totalLine = drawingMapper.queryDrawingNumByTitleLabClassifyState(
                                              title, 
                                              labels, 
                                              classifyId, 
                                              state, 
                                              null);
            Integer totalPage = totalLine;
            SearchWorkDrawingVO vo = SearchWorkDrawingVO.builder()
                                                        .drawing(drawingBOs.toArray(new SearchDrawingBO[drawingBOs.size()]))
                                                        .totalPage(totalPage)
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

    @Override
    public ResponseVO getDetail(Long id) {
        try {
            DrawingEntity drawingEntity = drawingMapper.queryDrawingById(id);
            if (drawingEntity == null) {
                return ResponseVO.builder()
                             .result(new Result("手绘已删除或不存在"))
                             .build();
            }
            UserEntity authorEntity = userMapper.queryUserById(drawingEntity.getAuthorId());
            String classifyName = classifyDrawingMapper.queryClassifyById(drawingEntity.getClassifyId()).getName();
            String[] images = drawingEntity.getUrl().split(", ");
            DetailAuthorBO author = DetailAuthorBO.builder()
                                                  .id(authorEntity.getId())
                                                  .nickname(authorEntity.getNickname())
                                                  .avatar(authorEntity.getAvatar())
                                                  .build();
            GetDetailDrawingVO vo = GetDetailDrawingVO.builder()
                                                      .author(author)
                                                      .title(drawingEntity.getTitle())
                                                      .introduction(drawingEntity.getIntroduction())
                                                      .images(images)
                                                      .likeNum(0)
                                                      .isLike(0)
                                                      .isCollect(0)
                                                      .labels(null)
                                                      .type(1)
                                                      .dateTime(DateFormatUtil.getDate(drawingEntity.getCreateTime()))
                                                      .status(drawingEntity.getState())
                                                      .classifyName(classifyName)
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
            List<DrawingEntity> darwingEntities = drawingMapper.queryDrawingByAuthorId(id, title, labels, classifyId, null, (page - 1) * pageSize, pageSize);
            List<MyDrawingBO> drawingBOs = new ArrayList<MyDrawingBO>();

            for (DrawingEntity drawingEntity: darwingEntities) {
                String cover = drawingEntity.getUrl().split(", ")[0];
                drawingBOs.add(
                    MyDrawingBO.builder()
                               .id(drawingEntity.getId())
                               .cover(cover)
                               .title(drawingEntity.getTitle())
                               .introduction(drawingEntity.getIntroduction())
                               .dateTime(DateFormatUtil.getDate(drawingEntity.getCreateTime()))
                               .likeNum(0)
                               .comment(0)
                               .status(drawingEntity.getState())
                               .build()
                );
            }
            
            Integer totalLine = drawingMapper.queryDrawingNumByAuthorId(id, title, labels, classifyId, null);
            Integer totalPage = totalLine;
            MyWorksDrawingVO vo = MyWorksDrawingVO.builder()
                                                .drawing(drawingBOs.toArray(new MyDrawingBO[drawingBOs.size()]))
                                                .totalPage(totalPage)
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

    /***
     * 获得分类
     * 
     * @return
     */
    @Override
    public Map<String, Object> getClassify() {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> data = new HashMap<>();
        try {
            List<ClassifyDrawingEntity> classifyDrawingEntities = classifyDrawingMapper.queryAllClassifyDrawing(0);
            List<ClassifyBO> classifys = new ArrayList<ClassifyBO>();
            for (ClassifyDrawingEntity c : classifyDrawingEntities) {
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
        DrawingVO req = (DrawingVO) work;
        String transImgToString = Arrays.asList(req.getImage()).toString();
        transImgToString = transImgToString.substring(1, transImgToString.length() - 1);
        HashMap<String, Result> response = new HashMap<>();
        DrawingEntity drawingEntity = DrawingEntity.builder().title(req.getTitle()).authorId(id).url(transImgToString)
                .classifyId(req.getClassify()).labels("").introduction(req.getIntroduction()).build();
        try {
            drawingMapper.insertDrawing(drawingEntity);
            Result result = new Result();
            response.put("result", result);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(e.getMessage());
            response.put("result", result);
            return response;
        }
    }

    public Result Collection(Map<String, String> req) {
        return null;
    }

    public Map<String, Result> deleteWork(Long id, Integer type, Long authorId) {
        Map<String, Result> response = new HashMap<>();
        try {
            //drawingMapper.updateDrawingState(id, 1);
            DrawingEntity drawingEntity = drawingMapper.queryDrawingById(id);
            if (drawingEntity.getAuthorId() != authorId) {
                Result result = new Result("not author");
                response.put("result", result);
                return response;
            }
            drawingMapper.realDelete(id);
            Result result = new Result();
            response.put("result", result);
        } catch (Exception e) {
            Result result = new Result(e.getMessage());
            response.put("result", result);
        }
        return response;
    }

}
