package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.ArticleService;
import ap.deepstroll.service.DrawingService;
import ap.deepstroll.service.WorkService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.DeleteWorkRequestVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class WorkController {

    @Autowired
    ArticleService articleService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
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
    public Map<String,Result> delete(@RequestBody DeleteWorkRequestVO request, @RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return articleService.deleteWork(request.getId(), request.getType(), Id);
    }
}
