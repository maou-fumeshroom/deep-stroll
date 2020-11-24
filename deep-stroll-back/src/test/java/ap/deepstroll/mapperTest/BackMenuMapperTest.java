package ap.deepstroll.mapperTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.BackMenuEntity;
import ap.deepstroll.mapper.BackMenuMapper;

@SpringBootTest
public class BackMenuMapperTest {
    
    @Autowired
    BackMenuMapper b;

    @Test
    void insertBackMenu() {
        BackMenuEntity bEntity = BackMenuEntity.builder()
                                        .name("name")
                                        .build();
        b.insertBackMenu(bEntity);
    }

    @Test
    void delete() {
        b.deleteBackMenuById(1);
    }

    @Test
    void query() {
        List<Integer> ids = Arrays.asList(2,3,4,5,6,7,8);
        System.out.println(b.queryById(ids));
    }

    @Test
    void queryByParentId() {
        System.out.println(b.queryByParentId(2));
    }
}
