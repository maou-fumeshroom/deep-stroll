package ap.deepstroll.mapperTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.RoleEntity;
import ap.deepstroll.mapper.RoleMapper;

@SpringBootTest
public class RoleMapperTest {
    
    @Autowired
    RoleMapper r;

    @Test
    void insertRole() {
        RoleEntity rEntity = RoleEntity.builder()
                                    .name("name")
                                    .introduction("introduction")
                                    .build();
        r.insertRole(rEntity);
    }

    @Test
    void query() {
        System.out.println(r.queryRole(null));
    }

    @Test
    void queryByIds() {
        List<Integer> roleIds = Arrays.asList(1, 2, 3);
        System.out.println(r.queryRoleByIds(roleIds, 0));
    }

    @Test
    void updateState() {
        r.updateRoleState(1, 1);
    }
}
