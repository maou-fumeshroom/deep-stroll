package ap.deepstroll.mapperTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.UserMapper;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    
    @Test
	void insertUser() {
        UserEntity userEntity = UserEntity.builder()
                                    .telephone("15169611397")
                                    .password("manxf")
                                    .sign("wnb")
                                    .build();
        System.out.println(userMapper.insertUser(userEntity));
    }

    @Test
    void queryUserById() {
        UserEntity userEntity = userMapper.queryUserById(1L);
        System.out.println(userEntity.toString());
    }

    @Test
    void queryUserByTel() {
        System.out.println(userMapper.queryUserByTel("15169611397").toString());
    }

    @Test
    void queryUser(){
        System.out.println(userMapper.queryUser("mxf", "mxf", null, null, 0, 1).toString());
    }

    @Test
    void updateUser() {
        UserEntity userEntity = UserEntity.builder()
                                    .id(1L)
                                    .password("password")
                                    .build();
        userMapper.updateUser(userEntity);
    }

    @Test
    void updateUserState() {
        userMapper.updateUserState(1L, 1);
    }

    @Test
    void updateUserVip() {
        userMapper.updateUserVip(1L, 1);
    }
    
}
