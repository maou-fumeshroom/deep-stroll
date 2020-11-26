package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.mapper.AdminMapper;

@SpringBootTest
public class AdminMapperTest {

    @Autowired
    AdminMapper a;

    @Test
    void insertAdmin() {
        AdminEntity aEntity = AdminEntity.builder()
                                    .account("account")
                                    .password("password")
                                    .build();
        a.insertAdmin(aEntity);
        System.out.println(aEntity.getId());
    }

    @Test
    void query() {
        System.out.println(a.queryAdminByAccount("account"));
    }

    @Test
    void queryAdmin() {
        System.out.println(a.queryAdmin(0, 0, 5));
        System.out.println(a.queryAdminNum(0));
    }

    @Test
    void updateAdminState() {
        a.updateAdminState(1, 1);
    }
    
}
