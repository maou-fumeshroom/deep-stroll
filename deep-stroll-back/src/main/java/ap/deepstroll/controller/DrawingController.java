package ap.deepstroll.controller;

import ap.deepstroll.service.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
