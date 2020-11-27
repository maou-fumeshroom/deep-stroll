package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.AdminService;
import ap.deepstroll.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserManagementService userManagementService;

    /**
     * 获取管理员角色测试通过
     * mwr
     * @return
     */
    @GetMapping("/admin/roleList")
    public Map<String,Object> getAdminRoleList(){
        return  adminService.getAdminRoleList();
    }

    /***
     * 删除管理员，实际更改管理员状态
     * mwr
     * @param id
     * @return
     */
    @PostMapping("/admin/delete")
    public Map<String,Result> deleteAdmin(@RequestBody Map<String,Integer> id){
        return adminService.deleteAdmin(id);
    }




}
