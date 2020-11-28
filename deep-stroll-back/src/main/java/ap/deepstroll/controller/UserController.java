package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.service.UserService;
import ap.deepstroll.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /**
     * 修改用户信息
     * @return
     */
    @PutMapping("/person/updateInfo")
    public Result updateUser(@RequestBody UserEntity userEntity){
        return userService.updateUser(userEntity);
    }

    //根据id获得用户全部信息
    @GetMapping("/person/info/{id}")
    public Map<String, Object> queryUserById(@PathVariable Long id){
        return userService.queryUserAllInfoById(id);
    }

    /**
     * 获取用户基本信息
     * @param
     * @return
     */
    @GetMapping("/person/basic")
    //@PreAuthorize("hasRole('admin')")
    public Map<String,Object> getUserBasicInfo(@RequestParam Long Id){
//        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
//        String id = jwtTokenUtil.getIdFromToken(token);
//        System.out.println(id);
//        Long Id = Long.valueOf(id);
        return userService.getUserBasicInfo(Id);
    }

    //因为目前评论功能没加，所以暂时没有搞获得点赞数评论数功能的函数

}
