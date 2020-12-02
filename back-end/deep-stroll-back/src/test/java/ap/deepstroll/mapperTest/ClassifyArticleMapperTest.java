package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.mapper.ClassifyArticleMapper;

@SpringBootTest
public class ClassifyArticleMapperTest {

    @Autowired
    ClassifyArticleMapper c;

    @Test
    void insertClassifyArticle() {
        c.insertClassifyArticle("lala");
    }

    @Test
    void getAllClassifyArticle() {
        System.out.println(c.queryAllClassifyArticle(1));
    }

    @Test
    void updateClassifyArticleState() {
        System.out.println(c.updateClassifyArticleState(1, 1));
    }
    
}
