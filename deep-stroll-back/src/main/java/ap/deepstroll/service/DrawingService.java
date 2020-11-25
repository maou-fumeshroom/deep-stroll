package ap.deepstroll.service;


import java.util.List;
import java.util.Map;

import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.Work;
import ap.deepstroll.mapper.DrawingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("drawingService")
public class DrawingService extends WorkService{
    @Autowired
    DrawingMapper drawingMapper;

    @Override
    public List browseWork(String title, String label, Integer classifyId, Integer state, Integer likeNum, Integer startIndex, Integer pageSize) {
        return drawingMapper.queryDrawingByTitleLabClassifyState(title,label,classifyId,state,startIndex,pageSize,likeNum);
    }

    @Override
    public List getClassify() {
        return null;
    }

    @Override
    public Work getDetail(Long id) {
        return drawingMapper.queryDrawingById(id);
    }

    @Override
    public Integer Publish(Work work) {
        return null;
    }

    public Integer Publish(DrawingEntity drawingEntity) {
        return drawingMapper.insertDrawing(drawingEntity);
    }


}
