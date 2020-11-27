package ap.deepstroll.service;


import ap.deepstroll.Exception.StateException;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.util.primeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.MacHebrew;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userManagementService")
public class UserManagementService {
    private static final Integer PAGE_SIZE = 10;
    @Autowired
    UserMapper userMapper;


    /***
     * 获取用户列表
     * mwr
     * @param req
     * @return
     */
    public Map<String,Object> getUserList(Map<String,Object> req){
        Map<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            //获取请求数据
            Integer page =((Integer) req.get("page"));
            if(page<1){
                data.put("totalPage", new Integer("0"));
                response.put("data",data);
                Result result = new Result("Invalid Page");
                response.put("result",result);
                return response;
            }
            page = (page -1)*10;
            String[] keys = req.get("key")==null? null:req.get("key").toString().split(" ");
            //存在一个前后端不一致
            Integer state = (Integer) req.get("status");
            Integer vip = (Integer) req.get("vip");
            //处理请求key字段
            String nickname = null;
            String telephone = null;
            if(keys!=null) {
                if (keys.length == 1) {
                    if (primeUtil.checkPhone(keys[0])) {
                        telephone = keys[0];
                    } else {
                        nickname = keys[0];
                    }
                } else if (keys.length == 2) {
                    if (primeUtil.checkPhone(keys[0])) {
                        telephone = keys[0];
                        nickname = keys[1];
                    } else if (primeUtil.checkPhone(keys[1])) {
                        telephone = keys[1];
                        nickname = keys[0];
                    } else {
                        nickname = keys[0] + keys[1];
                    }
                } else {
                    nickname = keys.toString();
                }
            }
            List<UserEntity> users = userMapper.queryUser(telephone, nickname, state, vip, page, PAGE_SIZE);
            Integer totalPage = (int) (Math.floor(users.size() / PAGE_SIZE)) + 1;
            Result result = new Result();
            data.put("totalPage",totalPage);
            data.put("users",users);
            response.put("data",data);
            response.put("result",result);
        }catch (Exception e){
            Integer totalPage = 0;
            data.put("users",null);
            data.put("totalPage",totalPage);
            Result result = new Result(e.getMessage());
            response.put("data",data);
            response.put("result",result);
        }
        return  response;
    }

    /***
     * 更改用户状态 status vip
     * mwr
     * @param req
     * @return
     */
    public Map<String,Result> changeUserState(Map<String,Object> req)  {
        String userId = req.get("id").toString();
        HashMap<String,Result> response = new HashMap<>();
        //不合法则删除
        if(userId.equals("null")){
            Result result = new Result("Invalid user id");
            response.put("result",result);
            return response;
        }
        try {
            //vip 和status 均存在
            if (req.keySet().contains("status") && req.keySet().contains("vip")) {
                Integer state = (Integer) req.get("status");
                Integer vip = (Integer) req.get("vip");
                if (!primeUtil.checkvalue(state, "userStates")) {
                    //state 不存在抛出异常
                    throw new StateException("status", state.toString());
                }
                if (!primeUtil.checkvalue(state, "vipStates")) {
                    //vip不存在抛出异常
                    throw new StateException("vip", vip.toString());
                }
                int stateAns = userMapper.updateUserState(Long.valueOf(userId), state);
                int vipAns = userMapper.updateUserVip(Long.valueOf(userId), vip);
                if (vipAns == 1 && stateAns == 1) {
                    Result result = new Result();
                    response.put("result", result);
                } else {
                    Result result = new Result("Database error");
                    response.put("result", result);
                }
            } else if (req.keySet().contains("status") && !req.keySet().contains("vip")) {
                Integer state = (Integer) req.get("status");
                if (!primeUtil.checkvalue(state, "userStates")) {
                    //state 不存在抛出异常
                    throw new StateException("status", state.toString());
                }
                int stateAns = userMapper.updateUserState(Long.valueOf(userId), state);
                if (stateAns == 1) {
                    Result result = new Result();
                    response.put("result", result);
                } else {
                    Result result = new Result("Database error");
                    response.put("result", result);
                }
            } else if (req.keySet().contains("vip") && !req.keySet().contains("status")) {
                Integer vip = (Integer) req.get("vip");
                if (!primeUtil.checkvalue(vip, "vipStates")) {
                    //vip 不存在抛出异常
                    throw new StateException("vip", vip.toString());
                }
                int vipAns = userMapper.updateUserVip(Long.valueOf(userId), vip);
                if (vipAns == 1) {
                    Result result = new Result();
                    response.put("result", result);
                } else {
                    Result result = new Result("Database error");
                    response.put("result", result);
                }
            } else{//如果不存在VIP 和status 字段
                Result result = new Result("No operation");
                response.put("result",result);
            }
        }catch (StateException e){
            //处理异常
            Result result = new Result(e.getMessage());
            response.put("result",result);
            return response;
        }
        return response;
    }

}
