package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.ArticleService;
import ap.deepstroll.service.DrawingService;
import ap.deepstroll.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WorkController {

    @Autowired
    WorkService workService;
    @Autowired
    ArticleService articleService;
    @Autowired
    DrawingService drawingService;
    /**
     * 添加收藏
     * @param req
     * @return
     */
    @PostMapping("/collection")
    public Result addCollection(@RequestBody Map req){
        return workService.addCollection(req);
    }

    @PostMapping("/works/delete")
    public Result delete(@RequestBody Long id,@RequestBody Integer type){
        if(type == 0){
            return articleService.deleteWork(id);
        }
        else {
            return drawingService.deleteWork(id);
        }
    }
}
