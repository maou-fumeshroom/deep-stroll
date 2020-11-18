package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.mapper.AdminRoleMapper;

@SpringBootTest
public class AdminRoleMapperTest {
    
    @Autowired
    AdminRoleMapper a;

    @Test
    void insert() {
        Integer[] roleId = {1,2,3,4,5,6};
        a.insertAdminRole(1, roleId);
    }
    
    @Test
    void queryByAdminId() {
        System.out.println(a.queryByAdminId(1));
    }
}
