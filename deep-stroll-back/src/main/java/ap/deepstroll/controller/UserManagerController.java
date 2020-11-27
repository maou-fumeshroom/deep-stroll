package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class UserManagerController {

    @Autowired
    UserManagementService userManagementService;
    /**
     * 浏览用户，包含条件搜索
     * mwr dei
     * @param req
     * @return
     */
    @PostMapping("admin/userManager/users")
    public Map<String,Object> getUsersList(@RequestBody Map<String,Object> req){
        return userManagementService.getUserList(req);
    }

    /**
     * 获取用户状态
     * mwr dei
     * @param req
     * @return
     */
    @PostMapping("admin/userManager/userstate")
    public Map<String, Result>updateUserState(@RequestBody Map<String,Object> req){
        return userManagementService.changeUserState(req);
    }
}
