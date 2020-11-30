package ap.deepstroll.service;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminThemeService")
public class AdminThemeService {
    @Autowired
    ThemeMapper themeMapper;

    /**
     * 获取主题列表
     * @return
     */
    public Map<String,Object> browserThemeList(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            List<ThemeEntity> theme = themeMapper.queryTheme();
            data.put("themes",theme);
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /***
     * 删除指定主题
     * @param id
     * @return
     */
    public Map<String ,Result> deleteThemeById(Integer id){
        HashMap<String,Result> response = new HashMap<>();
        try {
            themeMapper.deleteThemeById(id);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    public Map<String, Result> setDefaultTheme(Integer id){
        HashMap<String,Result> response = new HashMap<>();
        try {
            themeMapper.setDefaultTheme(id);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /***
     * 根据id获得主题
     */
//    public Map<String,Object> queryThemeById(Integer id){
//        HashMap<String,Object> response = new HashMap<>();
//        try {
//            ThemeEntity theme = themeMapper.queryThemeById(id);
//            response.put("data",theme);
//            response.put("result",new Result());
//        }catch (Exception e){
//            response.put("data",null);
//            response.put("result",new Result(e.getMessage()));
//        }
//        return response;
//    }

    /***
     * 管理员添加主题
     * 还没加log
     * @param
     * @return
     */
    public Map<String, Result> insertNewTheme(String name, String bg, String bgm){
        HashMap<String,Result> response = new HashMap<>();
        try {
            ThemeEntity themeEntity = new ThemeEntity();
            themeEntity.setName(name);
            themeEntity.setBackgroundUrl(bg);
            themeEntity.setBgmUrl(bgm);
            themeMapper.insertNewTheme(themeEntity);
            Result result=new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result=new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

}
