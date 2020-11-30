package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class UserManagerController {

    @Autowired
    UserManageService userManageService;
    /**
     * 获取用户列表，包含条件搜索
     * mwr dei
     * @param req
     * @return
     */
    @GetMapping("/api/admin/user")
    public Map<String,Object> getUsersList(@RequestParam String key,
                                           @RequestParam Integer status,
                                           @RequestParam Integer vip,
                                           @RequestParam Integer page){
        return userManageService.getUserList(key,status,vip,page);
    }

    /**
     * 更新用户状态
     * mwr dei
     * @param req
     * @return
     */
    @PostMapping("/api/admin/user/status")
    public Map<String, Result>updateUserState(@RequestBody Map<String,Object> req){
        return userManageService.changeUserState(req);
    }
}
