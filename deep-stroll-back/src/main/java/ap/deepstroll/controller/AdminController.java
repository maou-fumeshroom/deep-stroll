package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/admin/roleList")
    public Map<String,Object> getAdminRoleList(){
        return  adminService.getAdminRoleList();
    }

}
