package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.service.AdminThemeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String,Object> browserThemeList(){
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
    public Map<String, Result> deleteThemeById(@RequestParam Integer id){
        return adminThemeService.deleteThemeById(id);
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    @PostMapping("/api/admin/theme/default")
    public Map<String, Result> setDefaultTheme(@RequestParam Integer id){
        return adminThemeService.setDefaultTheme(id);
    }

    /***
     * 管理员添加主题
     * 还没加log
     * @param
     * @return
     */
    @PostMapping("/api/admin/theme/add")
    public Map<String, Result> insertNewTheme(@RequestBody String name, @RequestBody  String bg, @RequestBody  String bgm){
        return adminThemeService.insertNewTheme(name, bg, bgm);
    }
}
