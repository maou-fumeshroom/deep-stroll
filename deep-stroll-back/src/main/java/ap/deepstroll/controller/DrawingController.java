package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.service.DrawingService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.DrawingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class DrawingController {
    @Autowired
    DrawingService drawingService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    ///article/browse
//    @GetMapping("/api/drawing/browse")
//    public Map<String,Object> browseWork(){
//        return drawingService.browseWork();
//    }

    //获取详情
    @GetMapping("/api/drawing/detial")
    public Map<String,Object> getDetail(@RequestParam Long id){return drawingService.getDetail(id);}

    //分类浏览
    @GetMapping("/api/drawing/search")
    public Map<String,Object> searchWork(@RequestParam Integer page,
                                         @RequestParam String key,
                                         @RequestParam Integer classify,
                                         @RequestParam Integer status){
        return drawingService.searchWork(key, key,classify, status, page);
    }

    @PostMapping("/api/person/drawing/add")
    public Map Publish(@RequestBody DrawingVO req, @RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return drawingService.Publish(req,Id);
    }

    ///article/classify
    @GetMapping("/api/drawing/classify")
    public Map<String,Object> getClassify(){
        return drawingService.getClassify();
    }

    //mywork /api/person/drawing
    @GetMapping("/api/person/drawing")
    public Map<String,Object> myworks(@RequestParam String key,@RequestParam Integer classify,@RequestParam Integer page,@RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return drawingService.myWorks(Id,key,key,classify,page);
    }
}
