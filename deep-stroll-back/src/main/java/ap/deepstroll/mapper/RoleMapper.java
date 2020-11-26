package ap.deepstroll.mapper;

import ap.deepstroll.entity.RoleEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {

    /**
     * 插入新角色
     * @author mxf
     * @param roleEntity
     * @return
     */
    public Integer insertRole(RoleEntity roleEntity);

    /**
     * 查询全部角色
     * @author mxf
     * @param state
     * @return
     */
    public List<RoleEntity> queryRole(@Param("state")Integer state);

    /**
     * 更改角色状态
     * @author mxf
     * @param id
     * @param state
     * @return
     */
    public Integer updateRoleState(@Param("id")Integer id, @Param("state")Integer state);
    
}
