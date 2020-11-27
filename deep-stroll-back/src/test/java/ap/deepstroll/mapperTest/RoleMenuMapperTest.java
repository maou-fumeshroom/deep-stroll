package ap.deepstroll.mapperTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.mapper.RoleMenuMapper;

@SpringBootTest
public class RoleMenuMapperTest {
    
    @Autowired
    RoleMenuMapper r;

    @Test
    void insertRoleMenu() {
        Integer[] menuIds = {1, 2, 3, 4, 5, 6};
        r.insertRoleMenu(2, menuIds);
    }

    @Test
    void delete() {
        r.deleteRoleMenuByRoleMenuId(1);
    }

    @Test
    void queryByRoleIds() {
        List<Integer> roleIds = Arrays.asList(1);
        System.out.println(r.queryByRoleIds(roleIds));
    }

    @Test
    void queryMenuIdByRoleIds() {
        List<Integer> roleIds = Arrays.asList(1, 2);
        System.out.println(r.queryMenuIdByRoleIds(roleIds));
    }
}
