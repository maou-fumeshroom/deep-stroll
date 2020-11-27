package ap.deepstroll.service;

import java.util.HashMap;
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

    // /**
    //  * 用户登陆
    //  * @param req
    //  * @return
    //  */
    // public Map userLogin(Map req){
    //     return null;
    // }

    // /**
    //  * 用户注册
    //  * @param req
    //  * @return
    //  */
    // public Result userLogup(Map req){
    //     return null;
    // }

    /**
     * 修改用户信息
     * @author lqy
     * @param
     * @return
     */
      public Result updateUser(UserEntity userEntity){
//          Long id,String nickname,Integer gender,String email,String sign,String avatar
//          UserEntity userEntity = UserEntity.builder()
//                  .id(id)
//                  .nickname(nickname)
//                  .gender(gender)
//                  .eMail(email)
//                  .sign(sign)
//                  .avatar(avatar)
//                  .build();
          try {
              userMapper.updateUser(userEntity);
              Result result = new Result();
              return result;
          }catch (Exception e){
              Result result = new Result(e.getMessage());
              return result;
          }
    }

    /**
     * 获取全部用户信息
     * @param req
     * @return
     */
//    public Result getAllUserInfo(Map req){
//        return null;
//    }

    /***
     * 返回指定id的用户全部信息
     * @param id
     * @return
     */
    public Map<String,Object> queryUserById(Long id){
        HashMap<String,Object> response = new HashMap<>();
        try{
            UserEntity userEntity = userMapper.queryUserById(id);
            response.put("data",userEntity);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /***
     * 返回指定id用户信息:点赞还没写 所以暂时和上一个一样
     * @param id
     * @return
     */
    public Map<String,Object> queryUserInfoById(Long id){
        HashMap<String,Object> response = new HashMap<>();
        try{
            UserEntity userEntity = userMapper.queryUserById(id);
            response.put("data",userEntity);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /**
     * 冻结用户:需要log
     * @param
     * @return
     */
//    public String  updateUserState(Long id, Integer state){
//        return userMapper.updateUserState(id,state).toString();
//    }

}
