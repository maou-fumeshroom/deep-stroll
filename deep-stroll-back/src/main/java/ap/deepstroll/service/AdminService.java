package ap.deepstroll.service;

import java.util.*;

import ap.deepstroll.bo.AdminBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.AdminEntity;
import ap.deepstroll.entity.BackMenuEntity;
import ap.deepstroll.entity.RoleEntity;
import ap.deepstroll.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    BackMenuMapper backMenuMapper;
    @Autowired
    AdminRoleMapper adminRoleMapper;

    //正常状态下的管理员角色
    private static final int ROLE_VALID_STATE = 0;
    private static final int ROLE_INVALID_STATE = 1;

    //正常状态下管理员
    private static final Integer Admin_VALID_STATE = 0;
    private static final Integer Admin_INVALID_STATE = 1;
    private static final Integer PAGE_SIZE = 10;



    /**
     * 获取管理员角色
     * mwr dei
     * @param
     * @return
     */
    public Map<String,Object> getAdminRoleList(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        response.put("data",data);
        try{
            //数据库中原始的所有角色
            List<RoleEntity> primRole = roleMapper.queryRole(ROLE_VALID_STATE);
            //roles 代表所有角色，需要处理原始角色
            //map 用来存放id name 和rights
            ArrayList<Map> roles = new ArrayList<>();
            for (RoleEntity preRole : primRole) {
                HashMap<String,Object> realRole = new HashMap<>();
                realRole.put("id",preRole.getId());
                realRole.put("name",preRole.getName());
                //构建一个List role_id 为了查询menuid mapper 层这么规定的！！！！！！！！！
                List<Integer> roleIds = Arrays.asList(preRole.getId());
                //查询某个role 对应的menuid
                List<Integer> menuIds = roleMenuMapper.queryMenuIdByRoleIds(roleIds);
                // System.out.println("menuid"+menuIds);
                //如果role_menu 表没有找到
                if(menuIds.size()==0){
                    realRole.put("rights",null);
                    roles.add(realRole);
                    continue;
                }
                List<BackMenuEntity> menuEntitys = backMenuMapper.queryById(menuIds);
                //如果menu 没有找到
                if(menuEntitys.size()==0){
                    // System.out.println(preRole.getId());
                    realRole.put("rights",null);
                    roles.add(realRole);
                    continue;
                }
                List<Map> right = new ArrayList<>();
                for (BackMenuEntity mb: menuEntitys){
                    HashMap<String,Object> rightCell = new HashMap<>();
                    rightCell.put("id",(Integer)mb.getId());
                    rightCell.put("name",mb.getName());
                    right.add(rightCell);
                }
                realRole.put("rights",right);
                roles.add(realRole);
            }
            data.put("roles",roles);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e ){
            Result result = new Result(e.getMessage());
            response.put("data",null);
            response.put("result",result);
        }
        return response;
    }


    /**
     * 获取管理员列表
     * mwr
     * @param page
     * @return
     */
    public Map<String,Object> getAdminList(Integer page){
        HashMap<String,Object> response  = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        response.put("data",data);
        if (page<1){
            Integer totalPage =Integer.valueOf(0);
            Result result = new Result("Invalid page");
            data.put("admins",null);
            data.put("totalPage",totalPage);
            response.put("Result",result);
            return response;
        }
        try{
            List<AdminEntity> preAdmins = adminMapper.queryAdmin(Admin_VALID_STATE,(page-1)*PAGE_SIZE,PAGE_SIZE);
            Integer totalPage = adminMapper.queryAdminNum(Admin_VALID_STATE);
            List<AdminBO> admins= new ArrayList<>();
            for (AdminEntity a:preAdmins){
                List<Integer> roleIds = adminRoleMapper.queryRoleIdsByAdminId(a.getId());
                List<RoleEntity> roleEntityList = roleMapper.queryRoleByIds(roleIds,ROLE_VALID_STATE);          
                // ArrayList<String> roleNameList = new ArrayList<>();
                // for(RoleEntity re :roleEntityList){
                //     roleNameList.add(re.getName());
                // }
                AdminBO bo = AdminBO.builder().id(a.getId()).account(a.getAccount()).password(a.getPassword()).roleName(roleEntityList.get(0).getName()).build();
                admins.add(bo);
            }
            data.put("admins",admins);
            data.put("totalPage",totalPage);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e ){
            Integer totalPage = Integer.valueOf(0);
            Result result = new Result(e.getMessage());
            data.put("admins",null);
            data.put("totalPage",totalPage);
            response.put("Result",result);
        }
        return response;
    }


    /**
     * 添加管理员
     * @param adminEntity
     * lqy
     * mwr change
     * @return
     */
    // public Map<String,Result> addAdmin(AdminEntity adminEntity){
    //     HashMap<String,Result> response = new HashMap<>();
    //     try{
    //         int ans = adminMapper.insertAdmin(adminEntity);
    //         if(ans ==1){
    //             Result result = new Result();
    //             response.put("result",result);
    //         }else{
    //             Result result = new Result("Database error");
    //             response.put("result",result);
    //         }
    //     }catch (Exception e){
    //         Result result = new Result(e.getMessage());
    //         response.put("result",result);
    //     }
    //     return response;
    // }

    /**
     * 修改管理员状态
     * mwr dei
     * @param
     * @return
     */
    public Map<String,Result> deleteAdmin(Map<String,Integer> req){
        Map<String,Result> response = new HashMap<>();
        if (req.get("id") == Integer.valueOf(1)) {
            Result result =  new Result("forbidden");
                response.put("result",result);
        }
        try{
            int ans = adminMapper.deleteAdmin(req.get("id"));
            if(ans==1){
                Result result =  new Result();
                response.put("result",result);
            }else{
                Result result =  new Result("databaseErr");
                response.put("result",result);
            }
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

}
