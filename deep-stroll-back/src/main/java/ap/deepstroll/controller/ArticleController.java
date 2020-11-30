package ap.deepstroll.controller;


import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.service.ArticleService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @GetMapping("/api/article/detial")
    public Map<String,Object> getDetail(@RequestParam Long id){return articleService.getDetail(id);}

    //分类浏览
    @GetMapping("/api/artile/search")
    public Map<String,Object> searchWork(@RequestParam Integer page,
                                         @RequestParam String key,
                                         @RequestParam Integer classify,
                                         @RequestParam Integer status){
        return articleService.searchWork(key, key,
                classify, status, page);
    }

    //发布
    @PostMapping("/api/person/article/add")
    public Map Publish(@RequestBody ArticleVO req, @RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return articleService.Publish(req,Id);
    }

    ///article/classify
    @GetMapping("/api/article/classify")
    public Map<String,Object> getClassify(){
        return articleService.getClassify();
    }
}
