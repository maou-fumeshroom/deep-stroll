package ap.deepstroll.service;

import org.springframework.stereotype.Service;

import ap.deepstroll.entity.ArticleEntity;

import java.util.List;
import java.util.Map;

@Service("articleService")
public class ArticleService extends WorkService{

    @Override
    public ArticleEntity browseWork(Map req) {
        return null;
    }

    @Override
    public List getClassify() {
        return null;
    }

    @Override
    public ArticleEntity getDetail(int id) {
        return null;
    }

    @Override
    public ArticleEntity Publish(Map req) {
        return null;
    }

}
