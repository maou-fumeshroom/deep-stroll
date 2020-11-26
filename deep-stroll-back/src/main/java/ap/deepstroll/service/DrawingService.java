package ap.deepstroll.service;


import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.DrawingMapper;
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

    @Override
    //result!!!
    public List<DrawingEntity> browseWork(){
        return drawingMapper.queryDrawingByTitleLabClassifyState(null,null,null,0,null,null,null);
    }

    @Override
    //result!!!
    public List searchWork(String title, String label, Integer classifyId, Integer state, Integer likeNum, Integer page) {
        Integer startIndex= this.pageSize * (page -1);
        return drawingMapper.queryDrawingByTitleLabClassifyState(title,label,classifyId,state,startIndex,this.pageSize,likeNum);
    }

    @Override
    public List getClassify() {
        return null;
    }

    @Override
    public DrawingEntity getDetail(Long id) {
        return drawingMapper.queryDrawingById(id);
    }

    public Result Collection(Map<String,String> req){
       return null;
    }

//    public Integer Publish(DrawingEntity drawingEntity) {
//        return drawingMapper.insertDrawing(drawingEntity);
//    }


}
