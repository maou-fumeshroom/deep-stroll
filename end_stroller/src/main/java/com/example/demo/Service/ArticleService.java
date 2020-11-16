package com.example.demo.Service;

import com.example.demo.Entity.Article;
import com.example.demo.Entity.Drawing;
import com.example.demo.Entity.Result;
import com.example.demo.Entity.Work;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleService extends WorkService{

    @Override
    public Article browseWork(Map req) {
        return null;
    }

    @Override
    public List getClassify() {
        return null;
    }

    @Override
    public Article getDetail(int id) {
        return null;
    }

    @Override
    public Article Publish(Map req) {
        return null;
    }

}
