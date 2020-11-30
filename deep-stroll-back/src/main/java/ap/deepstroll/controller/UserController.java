package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.UserBO;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.service.UserService;
import ap.deepstroll.utils.JwtTokenUtil;
import ap.deepstroll.vo.request.UserVo;
import org.apache.http.Header;
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
    @PutMapping("/api/person/updateInfo")
    public Result updateUser(@RequestBody UserVo req,@RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return userService.updateUser(req,Id);

    }

    //根据id获得用户全部信息
    @GetMapping("/api/person/totalInfo")
    public Map<String, Object> queryAllUserById(@RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return userService.queryUserAllInfoById(Id);
    }

    //根据id获得用户信息
    @GetMapping("/api/person/info")
    public Map<String, Object> queryUserById(@RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return userService.queryUserInfoById(Id);
    }

    /**
     * 获取用户基本信息
     * @param
     * @return
     */
    @GetMapping("/api/person/basic")
    //@PreAuthorize("hasRole('admin')")
    public Map<String,Object> getUserBasicInfo(@RequestHeader HttpHeaders headers){
        String token = headers.get("Authorization").get(0).substring("Bearer ".length());
        String id = jwtTokenUtil.getIdFromToken(token);
        Long Id = Long.valueOf(id);
        return userService.getUserBasicInfo(Id);
    }



    //因为目前评论功能没加，所以暂时没有搞获得点赞数评论数功能的函数

}
