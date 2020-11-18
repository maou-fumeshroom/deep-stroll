package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.AdminEntity;

@Mapper
public interface AdminMapper {

    /**
     * 插入管理员信息
     * @author mxf
     * @param adminEntity
     * @return
     */
    public Integer insertAdmin(AdminEntity adminEntity);
    
    /**
     * 查询全部管理员
     * @author mxf
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<AdminEntity> queryAdmin(
        @Param("state")Integer state,
        @Param("startIndex")Integer startIndex, 
        @Param("pageSize")Integer pageSize
    );

    /**
     * 查询全部管理员数目
     * @author mxf
     * @return
     */
    public Integer queryAdminNum(@Param("state")Integer state);

    /**
     * 修改管理员状态
     * @author mxf
     * @param id
     * @param state
     * @return
     */
    public Integer updateAdminState(@Param("id")Integer id, @Param("state")Integer state);

}
