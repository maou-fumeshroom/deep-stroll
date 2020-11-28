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
    @GetMapping("/admin/themeList")
    public Map<String,Object> browserThemeList(){
        return adminThemeService.browserThemeList();
    }

    /***
     * 根据id获得指定主题
     * @param id
     * @return
     */
    @GetMapping("/admin/theme")
    public Map<String,Object> queryThemeById(@RequestParam Integer id){
        return adminThemeService.queryThemeById(id);
    }

    /***
     * 删除指定主题
     * @param id
     * @return
     */
    @PostMapping("/admin/theme/delete")
    public Result deleteThemeById(@RequestParam Integer id){
        return adminThemeService.deleteThemeById(id);
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    @PostMapping("/admin/theme/default")
    public Result setDefaultTheme(@RequestParam Integer id){
        return adminThemeService.setDefaultTheme(id);
    }
}
