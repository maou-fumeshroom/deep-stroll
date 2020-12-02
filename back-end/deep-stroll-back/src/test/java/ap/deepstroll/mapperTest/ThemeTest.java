package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;

@SpringBootTest
public class ThemeTest {
    
    @Autowired
    ThemeMapper t;

    @Test
    void insertNewTheme() {
        ThemeEntity tEntity = ThemeEntity.builder()
                                    .name("name")
                                    .backgroundUrl("backgroundUrl")
                                    .bgmUrl("bgmUrl")
                                    .build();
        t.insertNewTheme(tEntity);    
    }

    @Test
    void delelteThemeById() {
        t.deleteThemeById(1);
    }

    @Test
    void queryTheme() {
        System.out.println(t.queryTheme());
    }

    @Test
    void queryThemeById() {
        System.out.println(t.queryThemeById(2));
    }

    @Test
    void setDefaultTheme() {
        t.setDefaultTheme(3);
    }

}
