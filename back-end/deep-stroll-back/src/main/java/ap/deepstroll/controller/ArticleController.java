package ap.deepstroll.controller;

import ap.deepstroll.service.ArticleService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.ArticleVO;
import ap.deepstroll.vo.response.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
//    //浏览
//    @GetMapping("/api/article/browse")
//    public Map<String,Object> browseWork(){
//        return articleService.browseWork();
//    }

    //获取详情
    @GetMapping("/api/article/detail")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public ResponseVO getDetail(@RequestParam Long id){return articleService.getDetail(id);}

    //分类浏览
    @GetMapping("/api/article/search")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public ResponseVO searchWork(@RequestParam Integer page,
                                         @RequestParam(required = false) String key,
                                         @RequestParam(required = false) Integer classify,
                                         @RequestParam(required = false) Integer status){
        return articleService.searchWork(key, key,classify, status, page);
    }

    //发布
    @PostMapping("/api/person/article/add")
    @PreAuthorize("hasAnyRole('common')")
    public Map Publish(@RequestBody ArticleVO req, @RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return articleService.Publish(req,Id);
    }

    ///article/classify
    @GetMapping("/api/article/classify")
    @PreAuthorize("hasAnyRole('common','chiefAdmin','workAdmin')")
    public Map<String,Object> getClassify(){
        return articleService.getClassify();
    }

    @GetMapping("/api/person/article")
    @PreAuthorize("hasRole('common')")
    public ResponseVO myWorks(
        @RequestParam(required = false) String key,
        @RequestParam(required = false) Integer classify,
        @RequestParam Integer page,
        @RequestHeader HttpHeaders headers
    ) {
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return articleService.myWorks(Id,key,key,classify,page);
    }
}
