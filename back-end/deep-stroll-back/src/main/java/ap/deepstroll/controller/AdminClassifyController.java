package ap.deepstroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ap.deepstroll.service.ClassifyService;
import ap.deepstroll.vo.request.AddClassifyAdminRequestVO;
import ap.deepstroll.vo.response.ResponseVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
public class AdminClassifyController {
    
    @Autowired
    ClassifyService classifyService;

    @GetMapping("/api/admin/classify")
    public ResponseVO getClassifyAdmin(
        @RequestParam Integer type,
        @RequestParam Integer page,
        @RequestParam(required = false) Integer status
    ) {
        return classifyService.getClassify(type, page, status);
    }

    @PostMapping("/api/admin/classify/add") 
    public ResponseVO addClassifyAdmin(@RequestBody AddClassifyAdminRequestVO addClassifyAdminVO) {
        return classifyService.addClassify(addClassifyAdminVO);
    }
    
}
