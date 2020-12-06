package ap.deepstroll.controller;

import ap.deepstroll.service.DrawingService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.DrawingVO;
import ap.deepstroll.vo.response.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin
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
    @GetMapping("/api/drawing/detail")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public ResponseVO getDetail(@RequestParam Long id){return drawingService.getDetail(id);}

    //分类浏览
    @GetMapping("/api/drawing/search")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public ResponseVO searchWork(@RequestParam Integer page,
                                         @RequestParam(required = false) String key,
                                         @RequestParam(required = false) Integer classify,
                                         @RequestParam(required = false) Integer status){
        return drawingService.searchWork(key, key,classify, status, page);
    }

    @PostMapping("/api/person/drawing/add")
    @PreAuthorize("hasRole('common')")
    public Map Publish(@RequestBody DrawingVO req, @RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
            return drawingService.Publish(req,Id);    
    }

    ///article/classify
    @GetMapping("/api/drawing/classify")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public Map<String,Object> getClassify(){
        return drawingService.getClassify();
    }

    @GetMapping("/api/person/drawing")
    @PreAuthorize("hasRole('common')")
    public ResponseVO myworks(
        @RequestParam(required = false) String key,
        @RequestParam(required = false) Integer classify,
        @RequestParam Integer page,
        @RequestHeader HttpHeaders headers
    ){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return drawingService.myWorks(Id,key,key,classify,page);
    }
}
