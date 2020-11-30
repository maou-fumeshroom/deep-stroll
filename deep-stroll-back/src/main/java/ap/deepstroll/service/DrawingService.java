package ap.deepstroll.service;


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

    @Override
    public Map<String,Object> browseWork(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByTitleLabClassifyState(null,null,null,0,0,10,null);
            data.put("drawing",drawingList);
            Result result = new Result();
            Integer totalPage= drawingMapper.queryDrawingNumByTitleLabClassifyState(null,null,null,0,null)/this.pageSize + 1;
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
    //result!!!
    public Map<String,Object> searchWork(String title, String label, Integer classifyId, Integer state, Integer likeNum, Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByTitleLabClassifyState(title,label,classifyId,state,startIndex,this.pageSize,likeNum);
            data.put("drawing",drawingList);
            Result result = new Result();
            Integer totalPage= drawingMapper.queryDrawingNumByTitleLabClassifyState(title,label,classifyId,state,likeNum)/this.pageSize + 1;
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

    public Result Collection(Map<String,String> req){
       return null;
    }

    public Result Publish(DrawingEntity drawingEntity) {
        try{
            drawingMapper.insertDrawing(drawingEntity);
            Result result = new Result();
            return result;
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            return result;
        }
    }

    public Result deleteWork(Long id) {
        try{
            drawingMapper.updateDrawingState(id,1);
            Result result = new Result();
            return result;
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            return result;
        }
    }

    /***
     *我的作品
     */
    public Map<String ,Object> myWorks(Long aurhorId,String title,String label,Integer classify,Integer page){
        Integer startIndex= this.pageSize * (page -1);
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<DrawingEntity> drawingList =  drawingMapper.queryDrawingByAuthorId(aurhorId,title,label,classify,0,startIndex,pageSize);
            data.put("drawings",drawingList);

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
