package ap.deepstroll.controller;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ThemeController {
    @Autowired
    ThemeService themeService;

    /**
     * user浏览内置主题
     * 如果是默认主题的话，就显示背景和音乐，否则只显示id和name
     * @return
     */
    @GetMapping("/config/themeList")
    public Map<String,Object> userBrowserThemeList(){
        return themeService.userBrowserThemeList();
    }

    /**
     * id 获取主题(背景音乐、背景图片)
     * @param id
     * @return
     */
    @GetMapping("/config/theme")
    public Map<String,Object> getThemeById(@RequestParam Integer id){
        return themeService.getThemeById(id);
    }

    /***
     * 用户添加主题
     * @param themeEntity
     * @return
     */
    @PostMapping("/config/theme/add")
    public Result insertNewTheme(@RequestBody ThemeEntity themeEntity){
        return themeService.insertNewTheme(themeEntity);
    }
}
