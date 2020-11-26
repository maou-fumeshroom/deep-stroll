package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.ThemeEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ThemeMapper {
    
   /**
    * 插入新主题
    * @author mxf
    * @param themeEntity
    * @return
    */ 
    public Integer insertNewTheme(ThemeEntity themeEntity);

    /**
     * 删除指定主题
     * @author mxf
     * @param id
     * @return
     */
    public Integer deleteThemeById(@Param("id")Integer id);

    /**
     * 获取全部主题
     * @author mxf
     * @return
     */
    public List<ThemeEntity> queryTheme();

    /**
     * 获取指定主题
     * @author mxf
     * @param id
     * @return
     */
    public ThemeEntity queryThemeById(@Param("id")Integer id);

    /**
     * 设定默认主题，原默认主题会被设为非默认
     * @author mxf
     * @param id
     * @return
     */
    public Integer setDefaultTheme(@Param("id")Integer id);
    
}
