package ap.deepstroll.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.entity.*;
import ap.deepstroll.mapper.ClassifyDrawingMapper;
import ap.deepstroll.mapper.DrawingMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.vo.DrawingVo;
import ap.deepstroll.vo.request.DrawingVO;
import ap.deepstroll.vo.request.WorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("drawingService")
public class DrawingService extends WorkService{
    Integer pageSize = 10;
    @Autowired
    DrawingMapper drawingMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClassifyDrawingMapper classifyDrawingMapper;

//    @Override
//    public Map<String,Object> browseWork(){
//        HashMap<String,Object> response = new HashMap<>();
//        HashMap<String,Object> data = new HashMap<>();
//        try {
//            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByTitleLabClassifyState(null,null,null,0,0,10,null);
//            data.put("drawing",drawingList);
//            Result result = new Result();
//            Integer totalPage= drawingMapper.queryDrawingNumByTitleLabClassifyState(null,null,null,0,null)/this.pageSize + 1;
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
    //result!!!
    public Map<String,Object> searchWork(String title, String label, Integer classifyId, Integer state,  Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByTitleLabClassifyState(title!="" ? title:null,label!="" ? label:null,classifyId,state,startIndex,this.pageSize,null);

            ArrayList <DrawingVo> drawingVOList = new ArrayList<>();
            for (int i = 0; i < drawingList.size();i++){
                UserEntity userEntity = userMapper.queryUserById(drawingList.get(i).getAuthorId());
                String classifyName = classifyDrawingMapper.queryClassifyById(drawingList.get(i).getClassifyId()).getName();
                String cover = drawingList.get(i).getUrl().split(";")[0];
                DrawingVo drawingVO = DrawingVo.builder()
                        .id(drawingList.get(i).getId())
                        .cover(cover)
                        .title(drawingList.get(i).getTitle())
                        .introduction(drawingList.get(i).getIntroduction())
                        .avatar(userEntity.getAvatar())
                        .nickName(userEntity.getNickname())
                        .dateTime(drawingList.get(i).getUpdateTime())
                        .comment("")
                        .status(drawingList.get(i).getState())
                        .classifyName(classifyName)
                        .build();
                drawingVOList.add(drawingVO);
            }
            data.put("drawing",drawingVOList);
            Result result = new Result();
            Integer totalPage= drawingMapper.queryDrawingNumByTitleLabClassifyState(title!="" ? title:null,label!="" ? label:null,classifyId,state,null)/this.pageSize + 1;
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
     * 获得分类
     * @return
     */
    @Override
    public Map<String,Object> getClassify(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<ClassifyDrawingEntity> classifyDrawingEntities= classifyDrawingMapper.queryAllClassifyDrawing(0);
            data.put("classify",classifyDrawingEntities);
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /***
     * aqing
     * @param id
     * @return
     */
    @Override
    public Map<String,Object> getDetail(Long id) {
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        HashMap<String,Object> author = new HashMap<>();
        try {
            DrawingEntity drawingDetial = drawingMapper.queryDrawingById(id);
            Long authorId = drawingDetial.getAuthorId();
            //System.out.println("authorId" + drawingDetial.getAuthorId());
            UserEntity userInfo = userMapper.queryUserById(authorId);
            author.put("id",userInfo.getId());
            //System.out.println("id:"+ userInfo.getId());
            author.put("nickname",userInfo.getNickname());
            author.put("avatar",userInfo.getAvatar());
            Result result = new Result();
            data.put("author",author);
            data.put("drawingDetial",drawingDetial);
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
        DrawingVO req = (DrawingVO)work;
        String transImgToString = req.getImage().toString();
        transImgToString = transImgToString.substring(1,transImgToString.length()-1);
        HashMap<String,Result> response = new HashMap<>();
        DrawingEntity drawingEntity = DrawingEntity.builder().title(req.getTitle())
                .authorId(id)
                .url(transImgToString)
                .classifyId(req.getClassify())
                .labels(null)
                .introduction(req.getIntroduction()).build();
        try{
            drawingMapper.insertDrawing(drawingEntity);
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

    public Map<String,Result> deleteWork(Long id,Integer type) {
        Map<String,Result>response = new HashMap<>();
        try{
            drawingMapper.updateDrawingState(id,1);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /***
     *我的作品
     */
    public Map<String ,Object> myWorks(Long aurhorId,String title,String label,Integer classify,Integer page){
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByTitleLabClassifyState(title!="" ? title:null,label!="" ? label:null,classify,0,startIndex,this.pageSize,null);
            ArrayList <DrawingVo> drawingVOList = new ArrayList<>();
            for (int i = 0; i < drawingList.size();i++){
                UserEntity userEntity = userMapper.queryUserById(drawingList.get(i).getAuthorId());
                String classifyName = classifyDrawingMapper.queryClassifyById(drawingList.get(i).getClassifyId()).getName();
                String cover = drawingList.get(i).getUrl().split(";")[0];
                DrawingVo drawingVO = DrawingVo.builder()
                        .id(drawingList.get(i).getId())
                        .cover(cover)
                        .title(drawingList.get(i).getTitle())
                        .introduction(drawingList.get(i).getIntroduction())
                        .avatar(userEntity.getAvatar())
                        .nickName(userEntity.getNickname())
                        .dateTime(drawingList.get(i).getUpdateTime())
                        .comment("")
                        .status(drawingList.get(i).getState())
                        .classifyName(classifyName)
                        .build();
                drawingVOList.add(drawingVO);
            }
            data.put("drawing",drawingVOList);
            Result result = new Result();
            Integer totalPage= drawingMapper.queryDrawingNumByAuthorId(aurhorId,title,label,classify,0)/this.pageSize+1;
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
}
