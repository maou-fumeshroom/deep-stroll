package ap.deepstroll.controller;

import ap.deepstroll.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class DrawingController {
    @Autowired
    WorkService drawingService;

}
