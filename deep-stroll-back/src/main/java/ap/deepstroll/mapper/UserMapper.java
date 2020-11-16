package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.UserEntity;

@Mapper
public interface UserMapper {

    /**
     * 插入新用户信息
     * @param userEntity
     */
    public void insertUser(UserEntity userEntity);
    
    /**
     * 返回指定id的用户信息
     * @author mxf
     * @param id
     * @return
     */
    public UserEntity queryUserById(@Param("id")Long id);

    /**
     * 
     * @return
     */
    //TODO
    public List<UserEntity> queryUser();

    /**
     * 修改用户信息
     * @param userEntity
     */
    public void updateUser(UserEntity userEntity);

    /**
     * 更新用户状态
     * @param id
     * @param state
     */
    public void updateUserState(@Param("id")Long id, @Param("state")int state);

}
