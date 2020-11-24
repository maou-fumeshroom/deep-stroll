package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.mapper.HomeMenuMapper;
import ap.deepstroll.entity.HomeMenuEntity;

@SpringBootTest
public class HomeMenuMapperTest {
    
    @Autowired
    HomeMenuMapper h;

    @Test
    void insertMenu() {
        HomeMenuEntity homeMenuEntity = HomeMenuEntity.builder()
                                                    .name("name")
                                                    .path("path")
                                                    .build();
        h.insertMenu(homeMenuEntity);                                      
    }

    @Test
    void queryMenu() {
        System.out.println(h.queryMenu());
    }

    @Test
    void updateMenuState() {
        h.updateMenuState(1, 1);
    }
    
}
