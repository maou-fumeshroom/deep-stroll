package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ap.deepstroll.entity.AdminEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {

    /**
     * 插入管理员信息
     * @author mxf
     * @param adminEntity
     * @return
     */
    public Integer insertAdmin(AdminEntity adminEntity);

    public Integer deleteAdmin(@Param("id")Integer id);

    /**
     * 通过账户查询管理员
     * @author mxf
     * @param account
     * @return
     */
    public AdminEntity queryAdminByAccount(@Param("account")String account);

    /**
     * 通过id查询管理员
     * @param id
     * @return
     */
    public AdminEntity queryAdminById(@Param("id")Integer id);
    
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
