package ap.deepstroll.controller;


import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    //浏览
    @GetMapping("/article/browse")
    public Map<String,Object> browseWork(){
        return articleService.browseWork();
    }

    //获取详情
    @GetMapping("/article/detial")
    public Map<String,Object> getDetail(@RequestParam Long id){return articleService.getDetail(id);}

    //分类浏览
    @GetMapping("/artile/search")
    public Map<String,Object> searchWork(@RequestParam Integer page,
                                         @RequestBody ArticleEntity articleEntity){
        return articleService.searchWork(articleEntity.getTitle(),
                articleEntity.getLabels(),
                articleEntity.getClassifyId(),
                articleEntity.getState(),
                articleEntity.getLikeNum(),
                page);
    }

    //发布
    @PostMapping("/person/article/add")
    public Result Publish(@RequestBody ArticleEntity articleEntity){
        return articleService.Publish(articleEntity);
    }

    ///article/classify
    @GetMapping("/article/classify")
    public Map<String,Object> getClassify(){
        return articleService.getClassify();
    }
}
