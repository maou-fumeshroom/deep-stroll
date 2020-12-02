package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.BackMenuEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BackMenuMapper {
    
    /**
     * 插入菜单
     * @author mxf
     * @param backMenuEntity
     * @return
     */
    public Integer insertBackMenu(BackMenuEntity backMenuEntity);

    /**
     * 删除指定菜单
     * @author mxf
     * @param id
     * @return
     */
    public Integer deleteBackMenuById(@Param("id")Integer id);

    /**
     * 查询一系列指定id的菜单信息
     * @author mxf
     * @param ids
     * @return
     */
    public List<BackMenuEntity> queryById(@Param("ids")List<Integer> ids);

    /**
     * 查询指定爸爸的儿子们
     * @author mxf
     * @param parentId
     * @return
     */
    public List<BackMenuEntity> queryByParentId(@Param("parentId")Integer parentId);

}
