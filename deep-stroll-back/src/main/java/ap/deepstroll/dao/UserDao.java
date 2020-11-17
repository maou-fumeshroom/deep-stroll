package ap.deepstroll.dao;

import java.util.Map;

import ap.deepstroll.entity.UserEntity;

public class UserDao {
    //DONE
    /**
     *  根据id获取用户信息
     */
    public UserEntity getUserById(int id) {
        return null;
    }

    //DONE
    /**
     *添加用户
     */
    public boolean addUser(Map userInfo){
        return false;
    }

    //DONE
    /**
     * 修改用户信息
     */
    public boolean changeUser(Map changeUserInfo){
        return false;
    }

    //DONE
    /**
     *获取全部用户信息
     */
    public Map getAllUserInfo(){
        return null;
    }

    //DONE
    /**
     *冻结用户
     */
    public boolean changeUserState(Integer id){
        return false;
    }

}
