package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 修改用户信息
     * @return
     */
    @PutMapping("/person/updateInfo")
    public Result updateUser(@RequestBody UserEntity userEntity){
        return userService.updateUser(userEntity);
    }

    /**
     * 修改用户状态
     * @return
     */
//    @PutMapping("/admin/user/{id}/{state}")
//    public String changeUserState(@PathVariable Long id, @PathVariable Integer state){
//        return userService.updateUserState(id, state);
//    }

    //根据id获得用户全部信息
    @GetMapping("/person/info/{id}")
    public Map<String, Object> queryUserById(@PathVariable Long id){
        return userService.queryUserById(id);
    }

    //因为目前评论功能没加，所以暂时没有搞获得点赞数评论数功能的函数

}
