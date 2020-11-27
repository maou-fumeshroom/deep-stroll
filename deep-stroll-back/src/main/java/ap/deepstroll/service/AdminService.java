package ap.deepstroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.entity.RoleEntity;
import ap.deepstroll.mapper.AdminMapper;
import ap.deepstroll.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;

    //正常状态下的管理员
    private static final int ROLE_VALID_STATE = 0;
    private static final int ROLE_INVALID_STATE = 1;

    //正常状态下管理员的角色
    private static final Integer Admin_VALID_STATE = 0;
    private static final Integer Admin_INVALID_STATE = 1;

    /**
     * 获取管理员列表
     * 暂时startindex搞了0
     * @param page
     * @return
     */
    public List<AdminEntity> getAdminList(Integer page){
        return adminMapper.queryAdmin(0,(page-1)*10,10);
    }

    /**
     * 获取管理员角色
     * mwr
     * @param
     * @return
     */
    public Map<String,Object> getAdminRoleList(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,List> roles = new HashMap<>();
        try{
            List<RoleEntity> roleList = roleMapper.queryRole(ROLE_VALID_STATE);
            roles.put("roles",roleList);
            Result result = new Result();
            response.put("data",roles);
            response.put("result",result);
            System.out.println();
        }catch (Exception e ){
            Result result = new Result(e.getMessage());
            response.put("data",null);
            response.put("result",result);
        }
        return response;
    }

    /**
     * 添加管理员
     * @param adminEntity
     * @return
     */
    public Result addAdmin(AdminEntity adminEntity){
        try{
            adminMapper.insertAdmin(adminEntity);
            Result result = new Result();
            return result;
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            return result;
        }
    }

    /**
     * 修改管理员状态
     * mwr
     * @param
     * @return
     */
    public Map<String,Result> deleteAdmin(Map<String,Integer> req){
        Map<String,Result> response = new HashMap<>();
        int ans =  adminMapper.updateAdminState(req.get("id"),Admin_INVALID_STATE);
        System.out.println(ans);
        if(ans==1){
            Result result =  new Result();
            response.put("result",result);
        }else{
            Result result =  new Result("databaseErr");
            response.put("result",result);
        }
        return response;
    }

}
