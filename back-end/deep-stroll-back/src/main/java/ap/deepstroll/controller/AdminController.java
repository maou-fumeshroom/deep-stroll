package ap.deepstroll.controller;

import ap.deepstroll.annotation.OperationLogAnnotation;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.service.AdminService;
import ap.deepstroll.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AdminController {//管理员管理
    @Autowired
    AdminService adminService;
    @Autowired
    UserManageService userManageService;

    /**
     * 获取管理员角色测试通过
     * mwr dei
     * @return
     */
    @GetMapping("/api/admin/role")
    @PreAuthorize("hasAnyRole('chiefAdmin','rightAdim')")
    public Map<String,Object> getAdminRoleList(){
        return  adminService.getAdminRoleList();
    }

    /**
     * 获取管理员列表
     * mwr dei
     * @param
     * @return
     */
    @GetMapping("/api/admin/list")
    @PreAuthorize("hasAnyRole('chiefAdmin','rightAdim')")
    public Map<String,Object>getAdminList(@RequestParam Integer page){
        return adminService.getAdminList(page);
    }


    /***
     * 添加管理员
     * mwr
     */
    // @PostMapping("/api/admin/add")
    // @PreAuthorize("hasRole('admin')")
    // public Map<String,Result>addAdmin(@RequestBody AdminEntity req){
    //     return adminService.addAdmin(req);
    // }

    /***
     * 删除管理员，实际更改管理员状态
     * mwr dei
     * @param id
     * @return
     */
    @PostMapping("/api/admin/delete")
    @PreAuthorize("hasAnyRole('chiefAdmin','rightAdim')")
    @OperationLogAnnotation(operName = "删除管理员")
    public Map<String,Result> deleteAdmin(@RequestBody Map<String,Integer> id){
        return adminService.deleteAdmin(id);
    }

}
