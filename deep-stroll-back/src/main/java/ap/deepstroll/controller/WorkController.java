package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WorkController {

    @Autowired
    WorkService workService;

    /**
     * 添加收藏
     * @param req
     * @return
     */
    @PostMapping("/collection")
    public Result addCollection(@RequestBody Map req){
        return workService.addCollection(req);
    }
}
