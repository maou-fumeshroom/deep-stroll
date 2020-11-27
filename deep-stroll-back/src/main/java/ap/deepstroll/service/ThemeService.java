package ap.deepstroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("themeService")
public class ThemeService {
    @Autowired
    ThemeMapper themeMapper;

    /**
     * user浏览内置主题
     * @return
     */
//    public Map<String,Object> browserThemeList(){
//        List<ThemeEntity> theme = themeMapper.queryTheme();
//    }

    /**
     * id 获取主题(背景音乐、背景图片)
     * @param id
     * @return
     */
    public Map<String,Object> setThemeById(Integer id){
        HashMap<String,Object> response = new HashMap<>();
        try {
            ThemeEntity theme = themeMapper.queryThemeById(id);
            response.put("data",theme);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("result",new Result(e.getMessage()));
            response.put("data",null);
        }
        return response;
    }

    /***
     * lqy
     * @param themeEntity
     * @return
     */
    public Integer insertNewTheme(ThemeEntity themeEntity){
        return themeMapper.insertNewTheme(themeEntity);
    }

}
