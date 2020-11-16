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
        userMapper.insertUser(userEntity);
    }
    
}
