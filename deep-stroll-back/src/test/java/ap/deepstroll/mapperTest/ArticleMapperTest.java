package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.mapper.ArticleMapper;

@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    void insertArticle() {
        ArticleEntity a = ArticleEntity.builder()
                                .authorId(123L)
                                .title("test")
                                .introduction("test")
                                .url("test")
                                .labels("labels")
                                .classifyId(1)
                                .build();
        articleMapper.insertArticle(a);
    }

    @Test
    void queryArticleById() {
        System.out.println(articleMapper.queryArticleById(1L).toString());
    }

    @Test
    void queryArticle() {
        System.out.println(articleMapper.queryArticleByTitleLabState(null, null, 1, null, 0, 1, 0));
        System.out.println(articleMapper.queryArticleNumByTitleLabState(null, null, 1, null, 0));
    }

    @Test
    void updateState() {
        articleMapper.updateArticleState(1L, 1);
    }
    
    @Test
    void updateLike() {
        articleMapper.updateLikeNumById(1L, 1);
    }


}
