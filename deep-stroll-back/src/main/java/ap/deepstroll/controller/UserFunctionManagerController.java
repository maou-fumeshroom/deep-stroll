package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.UserFunctionManageService;
import ap.deepstroll.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserFunctionManagerController {

    @Autowired
    UserFunctionManageService userFunctionManageService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    /**
     *功能菜单列表
     * mwr
     * dei
     * @return
     */
    @GetMapping("/api/admin/menu")
    @PreAuthorize("hasRole('admin')")
    public Map<String,Object> getFunctionMenu(){
        return userFunctionManageService.getFunctionMenu();
    }

    /***
     * 设置功能状态
     * @param req
     * mwr dei
     * @return
     */
    @PostMapping("/api/admin/menu/status")
    @PreAuthorize("hasRole('admin')")
    public Map<String, Result>setMenuState(@RequestBody Map<String,Integer>req){
        return userFunctionManageService.setFunctionState(req);
    }




}