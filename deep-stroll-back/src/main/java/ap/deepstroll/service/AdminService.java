package ap.deepstroll.service;

import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.mapper.AdminMapper;

public class AdminService {
    AdminMapper adminMapper;

    /**
     * 获取管理员列表
     * 暂时startindex搞了0
     * @param page
     * @return
     */
    public List<AdminEntity> getAdminList(Integer page){
        return adminMapper.queryAdmin(0,0,page);
    }

    /**
     * 获取管理员角色
     * @param page
     * @return
     */
    public Map getAdminRoleList(int page){
        return null;
    }

    /**
     * 添加管理员
     * @param adminEntity
     * @return
     */
    public Integer addAdmin(AdminEntity adminEntity){
        return adminMapper.insertAdmin(adminEntity);
    }

    /**
     * 修改管理员状态
     * @param id
     * @return
     */
    public Integer deleteAdmin(Integer id,Integer state){
        return adminMapper.updateAdminState(id, state);
    }


}
