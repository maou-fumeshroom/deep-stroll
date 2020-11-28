package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/api/admin/user")
    public Map<String,Object> getUsersList(@RequestBody Map<String,Object> req){
        return userManageService.getUserList(req);
    }

    /**
     * 更新用户状态
     * mwr dei
     * @param req
     * @return
     */
    @PostMapping("admin/userManager/userstate")
    public Map<String, Result>updateUserState(@RequestBody Map<String,Object> req){
        return userManageService.changeUserState(req);
    }
}
