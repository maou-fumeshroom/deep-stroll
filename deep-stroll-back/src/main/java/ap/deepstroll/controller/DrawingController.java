package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.service.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class DrawingController {
    @Autowired
    DrawingService drawingService;

    ///article/browse
    @GetMapping("/drawing/browse")
    public Map<String,Object> browseWork(){
        return drawingService.browseWork();
    }

    //获取详情
    @GetMapping("/drawing/detial")
    public Map<String,Object> getDetail(@RequestParam Long id){return drawingService.getDetail(id);}

    //分类浏览
    @GetMapping("/drawing/search")
    public Map<String,Object> searchWork(@RequestParam Integer page, @RequestBody DrawingEntity drawingEntity){
        return drawingService.searchWork(drawingEntity.getTitle(),
                drawingEntity.getLabels(),
                drawingEntity.getClassifyId(),
                drawingEntity.getState(),
                drawingEntity.getLikeNum(),
                page);
    }

    @PostMapping("/person/drawing/add")
    public Result Publish(@RequestBody DrawingEntity drawingEntity){
        return drawingService.Publish(drawingEntity);
    }
}
