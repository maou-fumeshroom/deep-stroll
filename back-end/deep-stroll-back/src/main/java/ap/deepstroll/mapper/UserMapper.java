package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 插入新用户信息
     * @author mxf
     * @param userEntity
     */
    public Integer insertUser(UserEntity userEntity);
    
    /**
     * 返回指定id的用户信息
     * @author mxf
     * @param id
     * @return
     */
    public UserEntity queryUserById(@Param("id")Long id);

    /**
     * 查询符合条件的user
     * @param telephone
     * @param nickname
     * @param state
     * @param vip
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<UserEntity> queryUser(
        @Param("telephone")String telephone,
        @Param("nickname")String nickname,
        @Param("state")Integer state,
        @Param("vip")Integer vip,
        @Param("startIndex")Integer startIndex,
        @Param("pageSize")Integer pageSize
    );

    /**
     * 返回上一方法的总数
     * @param telephone
     * @param nickname
     * @param state
     * @param vip
     * @return
     */
    public Integer queryUserNum(
        @Param("telephone")String telephone,
        @Param("nickname")String nickname,
        @Param("state")Integer state,
        @Param("vip")Integer vip
    );
    
    /**
     * 通过手机号查询用户
     * @author mxf
     * @param telephone
     * @return
     */
    public UserEntity queryUserByTel(@Param("telephone")String telephone);

    /**
     * 修改用户信息
     * @author mxf
     * @param userEntity
     */
    public Integer updateUser(UserEntity userEntity);

    /**
     * 更新用户状态
     * @author mxf
     * @param id
     * @param state 0正常 1冻结
     */
    public Integer updateUserState(@Param("id")Long id, @Param("state")int state);

    /**
     * 更新用户vip状态
     * @author mxf
     * @param id
     * @param vip 0普通 1vip
     */
    public Integer updateUserVip(@Param("id")Long id, @Param("vip")int vip);

}
