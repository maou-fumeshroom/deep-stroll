package ap.deepstroll.controller;

import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.service.DrawingService;
import ap.deepstroll.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class DrawingController {
    @Autowired
    DrawingService drawingService;
    ///article/browse
    @GetMapping("/drawing/browse")
    public Map<String,Object> browseWork(){
        return drawingService.browseWork();
    }

    @GetMapping("/drawing/detial/{id}")
    public Map<String,Object> getDetail(@PathVariable Long id){return drawingService.getDetail(id);}
}
