package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.ArticleService;
import ap.deepstroll.service.DrawingService;
import ap.deepstroll.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
//    /**
//     * 添加收藏
//     * @param req
//     * @return
//     */
//    @PostMapping("/api/collect")
//    public Result addCollection(@RequestBody Map req){
//        return workService.addCollection(req);
//    }

    @PostMapping("/api/person/works/delete")
    @PreAuthorize("hasRole('common')")
    public Map<String,Result> delete(@RequestBody Map<Object, Object> requestBody){
        return articleService.deleteWork(((Integer)requestBody.get("id")).longValue(), (Integer)requestBody.get("type"));
    }
}
