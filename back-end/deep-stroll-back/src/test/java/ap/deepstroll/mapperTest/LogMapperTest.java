package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.LogEntity;
import ap.deepstroll.mapper.LogMapper;

@SpringBootTest
public class LogMapperTest {
    
    @Autowired
    LogMapper l;

    @Test
    void insertLog() {
        LogEntity log = LogEntity.builder()
                                .adminId(1)
                                .type(0)
                                .tableName("log")
                                .content("test")
                                .build();
        l.insertLog(log);
    }

    @Test
    void queryLog() {
        System.out.println(l.queryLog(0, 2));
    }

    @Test
    void queryByCondition() {
        System.out.println(l.queryLogByAdminIdTypeTableNameTime(null, null, "log", null, null, 0, 3));
    }

}
