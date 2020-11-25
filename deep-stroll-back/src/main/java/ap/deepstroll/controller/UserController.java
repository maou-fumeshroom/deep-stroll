package ap.deepstroll.controller;

import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 修改用户信息
     * @author lqy
     * @return
     */
    @PutMapping("/person/updateInfo")
    public Integer updateUser(@RequestBody UserEntity userEntity){
        return userService.updateUser(userEntity);
    }

    /**
     * 修改用户状态
     * @author lqy
     * @return
     */
    @PutMapping("/admin/user/{id}/{state}")
    public String changeUserState(@PathVariable Long id, @PathVariable Integer state){
        return userService.updateUserState(id, state);
    }

    @GetMapping("/person/info/{id}")
    public UserEntity queryUserById(@PathVariable Long id){
        return userService.queryUserById(id);
    }

}
