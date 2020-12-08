package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.mapper.ClassifyDrawingMapper;

@SpringBootTest
public class ClassifyDrawingMapperTest {
    
    @Autowired
    ClassifyDrawingMapper c;

    @Test
    void insertClassifyDrawing() {
        c.insertClassifyDrawing("lala");
    }

    @Test
    void query() {
        System.out.println(c.queryAllClassifyDrawing(null));
    }

    @Test
    void updateClassifDrawingState() {
        c.updateClassifyDrawingState(1, 12);
        
    }
}
