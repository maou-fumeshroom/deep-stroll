package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.RoleMenuEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMenuMapper {

    /**
     * 插入新角色及其菜单
     * @author mxf
     * @param roleId
     * @param menuId
     * @return
     */
    public Integer insertRoleMenu(
        @Param("roleId")Integer roleId,
        @Param("menuIds")Integer[] menuId
    );

    public Integer deleteRoleMenuByRoleMenuId(@Param("roleMenuId")Integer roleMenuId);

    /**
     * 查询指定角色对应的菜单
     * @author mxf
     * @param roleIds
     * @return
     */
    public List<RoleMenuEntity> queryByRoleIds(@Param("roleIds")List<Integer> roleIds);
    
    /**
     * 查询指定角色对应的菜单id
     * @author mxf
     * @param ids
     * @return
     */
    public List<Integer> queryMenuIdByRoleIds(@Param("roleIds")List<Integer> roleIds);

}
