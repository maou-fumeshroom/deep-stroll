package ap.deepstroll.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("themeService")
public class ThemeService {
    @Autowired
    ThemeMapper themeMapper;

    /**
     * user浏览内置主题
     * 如果是默认主题的话，就显示背景和音乐，否则只显示id和name
     * @return
     */
    public Map<String,Object> userBrowserThemeList(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<ThemeEntity> theme = themeMapper.queryTheme();

            ArrayList themes = new ArrayList();
            Map<String,Object> defaultTheme = new HashMap<>();
            for (int i = 0;i < theme.size(); i ++){
                if(theme.get(i).getIsDefault() == 0){
                    Map<String,Object> notDefaultThemes = new HashMap<>();
                    notDefaultThemes.put("id",theme.get(i).getId());
                    notDefaultThemes.put("name",theme.get(i).getName());
                    themes.add(notDefaultThemes);
                }
                else {
                    defaultTheme.put("id",theme.get(i).getId());
                    defaultTheme.put("name",theme.get(i).getName());
                    defaultTheme.put("background",theme.get(i).getBackgroundUrl());
                    defaultTheme.put("bgm",theme.get(i).getBgmUrl());
                }
            }
            data.put("themes",themes);
            data.put("default",defaultTheme);
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /**
     * id 获取主题(背景音乐、背景图片)
     * @param id
     * @return
     */
    public Map<String,Object> getThemeById(Integer id){
        HashMap<String,Object> response = new HashMap<>();
        try {
            ThemeEntity theme = themeMapper.queryThemeById(id);
            Map<String,Object> themeInfo = new HashMap<>();
            themeInfo.put("background",theme.getBackgroundUrl());
            themeInfo.put("bgm",theme.getBgmUrl());
            response.put("data",themeInfo);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("result",new Result(e.getMessage()));
            response.put("data",null);
        }
        return response;
    }

    /***
     * 用户添加主题
     * @param themeEntity
     * @return
     */
    public Result insertNewTheme(ThemeEntity themeEntity){
        try {
            themeMapper.insertNewTheme(themeEntity);
            Result result=new Result();
            return result;
        }catch (Exception e){
            Result result=new Result(e.getMessage());
            return result;
        }
    }

}
