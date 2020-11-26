package ap.deepstroll.service;

import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.dao.UserDao;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 用户登陆
     * @param req
     * @return
     */
    public Map userLogin(Map req){
        return null;
    }

    /**
     * 用户注册
     * @param req
     * @return
     */
    public Result userLogup(Map req){
        return null;
    }

    /**
     * 修改用户信息
     * @author lqy
     * @param userEntity
     * @return
     */
    public Integer updateUser(UserEntity userEntity){
        return userMapper.updateUser(userEntity);
    }

    /**
     * 获取全部用户信息
     * @param req
     * @return
     */
    public Result getAllUserInfo(Map req){
        return null;
    }

    /***
     * 返回指定id的用户信息
     * @param id
     * @return
     */
    public UserEntity queryUserById(Long id){
        System.out.println(id);
        System.out.println(id.getClass());
        try{
            UserEntity userEntity = userMapper.queryUserById(1L);
            System.out.println("this is test"+userEntity.toString());
            return userEntity;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 冻结用户:需要log
     * @param
     * @return
     */
    public String  updateUserState(Long id, Integer state){
        return userMapper.updateUserState(id,state).toString();
    }

}
