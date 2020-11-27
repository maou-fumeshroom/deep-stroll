package ap.deepstroll.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.DrawingMapper;
import ap.deepstroll.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("drawingService")
@Component
public class DrawingService extends WorkService{
    Integer pageSize = 10;
    @Autowired
    DrawingMapper drawingMapper;
    @Autowired
    UserMapper userMapper;

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

    @Override
    public List getClassify() {
        return null;
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

//    public Integer Publish(DrawingEntity drawingEntity) {
//        return drawingMapper.insertDrawing(drawingEntity);
//    }


}
