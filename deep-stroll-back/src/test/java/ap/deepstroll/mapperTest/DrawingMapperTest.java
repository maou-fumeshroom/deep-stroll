package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.mapper.DrawingMapper;

@SpringBootTest
public class DrawingMapperTest {
    
    @Autowired
    DrawingMapper d;

    @Test
    void insertDrawing() {
        DrawingEntity dEntity = DrawingEntity.builder()
                                .authorId(123L)
                                .title("title")
                                .introduction("introduction")
                                .labels("labels")
                                .url("url")
                                .classifyId(12)
                                .build();
        d.insertDrawing(dEntity);
    }

    @Test
    void queryDrawingById() {
        System.out.println(d.queryDrawingById(1L));
    }

    @Test
    void queryDrawing() {
        System.out.println(d.queryDrawingByTitleLabClassifyState(null, null, 1, 0, 0, 10, null));
        System.out.println(d.queryDrawingNumByTitleLabClassifyState("title", "label", null, null, 0));
    }
    
    @Test
    void updateDrawingState() {
        d.updateDrawingState(1L, 12);
    }

    @Test
    void updateLikeNum() {
        d.updateLikeNumById(1L, 10);
    }
    
}