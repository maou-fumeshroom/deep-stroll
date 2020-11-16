package com.example.demo.Controller;

import com.example.demo.Service.WorkService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    WorkService articleService;

    public List test(){
        return articleService.getClassify();
    }
}
