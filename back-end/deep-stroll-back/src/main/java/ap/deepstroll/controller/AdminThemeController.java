package ap.deepstroll.controller;

import ap.deepstroll.annotation.OperationLogAnnotation;
import ap.deepstroll.bo.Result;
import ap.deepstroll.service.AdminThemeService;
import ap.deepstroll.vo.response.ResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AdminThemeController {
    @Autowired
    AdminThemeService adminThemeService;

    /**
     * 获取主题列表
     * @return
     */
    @GetMapping("/api/admin/theme")
    @PreAuthorize("hasAnyRole('chiefAdmin','themeAdmin')")
    public ResponseVO browserThemeList(){
        return adminThemeService.browserThemeList();
    }

    /***
     * 根据id获得指定主题
     * @param id
     * @return
     */
    //@GetMapping("/admin/theme")
    //public Map<String,Object> queryThemeById(@RequestParam Integer id){
    //    return adminThemeService.queryThemeById(id);
    //}

    /***
     * 删除指定主题
     * @param id
     * @return
     */
    @PostMapping("/api/admin/theme/delete")
    @PreAuthorize("hasAnyRole('chiefAdmin','themeAdmin')")
    @OperationLogAnnotation(operName = "删除主题")
    public Map<String, Result> deleteThemeById(@RequestBody Map<String,Integer> id){
        Integer Id = id.get("id");
        return adminThemeService.deleteThemeById(Id);
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    @PostMapping("/api/admin/theme/default")
    @PreAuthorize("hasAnyRole('chiefAdmin','themeAdmin')")
    public Map<String, Result> setDefaultTheme(@RequestBody Map<String,Integer> id){
        Integer Id = id.get("id");
        return adminThemeService.setDefaultTheme(Id);
    }

    /***
     * 管理员添加主题
     * 还没加log
     * @param
     * @return
     */
    @PostMapping("/api/admin/theme/add")
    @PreAuthorize("hasAnyRole('chiefAdmin','themeAdmin')")
    @OperationLogAnnotation(operName = "添加主题")
    public Map<String, Result> insertNewTheme(@RequestBody Map<String, String> body){
        return adminThemeService.insertNewTheme(body.get("name"), body.get("bg"), body.get("bgm"));
    }
}
