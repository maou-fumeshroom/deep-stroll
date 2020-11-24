package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.AdminRoleEntity;

@Mapper
public interface AdminRoleMapper {
    
    /**
     * 插入一个管理员以及其角色
     * @author mxf
     * @param adminId
     * @param roleId
     * @return
     */
    public Integer insertAdminRole(
        @Param("adminId")Integer adminId,
        @Param("roleIds")Integer[] roleId
    );

    /**
     * 删除指定admin role对应
     * @author mxf
     * @param adminRoleId
     * @return
     */
    public Integer deleteByAdminRoleId(@Param("adminRoleId")Integer adminRoleId);

    /**
     * 查询某管理员的角色
     * @author mxf
     * @param adminId
     * @return
     */
    public List<AdminRoleEntity> queryByAdminId(@Param("adminId")Integer adminId);

    /**
     * 查询某管理员的角色id
     * @author mxf
     * @param adminId
     * @return
     */
    public List<Integer> queryRoleIdsByAdminId(@Param("adminId")Integer adminId);
    
}
