package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.HomeMenuEntity;

@Mapper
public interface HomeMenuMapper {
    
    /**
     * 插入一条新的homeMenu
     * @author mxf
     * @param homeMenuEntity
     * @return
     */
    public Integer insertMenu(HomeMenuEntity homeMenuEntity);

    /**
     * 查询全部homeMenu
     * @author mxf
     * @return
     */
    public List<HomeMenuEntity> queryMenuByState(@Param("state")Integer state);

    /**
     * 更新menu状态
     * @author mxf
     * @param id
     * @param state
     * @return
     */
    public Integer updateMenuState(@Param("id")Integer id, @Param("state")Integer state);

}
