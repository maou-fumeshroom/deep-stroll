package ap.deepstroll.service;

import java.util.List;
import java.util.Map;

import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

public class ThemeService {
    ThemeMapper themeMapper;

    /**
     * 浏览内置主题
     * @return
     */
    public List<ThemeEntity> browserThemeList(){
        return themeMapper.queryTheme();
    }

    /**
     * id 获取主题(背景音乐、背景图片)
     * @param id
     * @return
     */
    public ThemeEntity setThemeById(Integer id){
        return themeMapper.queryThemeById(id);
    }

    /***
     * lqy
     * @param themeEntity
     * @return
     */
    public Integer insertNewTheme(ThemeEntity themeEntity){
        return themeMapper.insertNewTheme(themeEntity);
    }

    /***
     * 删除指定主题
     * @param id
     * @return
     */
    public Integer deleteThemeById(Integer id){
        return themeMapper.deleteThemeById(id);
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    public Integer setDefaultTheme(Integer id){
        return themeMapper.deleteThemeById(id);
    }
}
