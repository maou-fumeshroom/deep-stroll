package ap.deepstroll.controller;

import ap.deepstroll.annotation.OperationLogAnnotation;
import ap.deepstroll.bo.Result;
import ap.deepstroll.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
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
    @PreAuthorize("hasAnyRole('chiefAdmin','userAdmin')")
    public Map<String,Object> getUsersList(@RequestParam(required = false) String key,
                                           @RequestParam(required = false) Integer status,
                                           @RequestParam(required = false) Integer vip,
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
    @PreAuthorize("hasAnyRole('chiefAdmin','userAdmin')")
    @OperationLogAnnotation(operName = "更改用户状态")
    public Map<String, Result>updateUserState(@RequestBody Map<String,Object> req){
        return userManageService.changeUserState(req);
    }
}
