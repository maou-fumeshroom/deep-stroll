package ap.deepstroll.service;


import ap.deepstroll.Exception.StateException;
import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.UserBO;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.util.primeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userManageService")
public class UserManageService {
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    UserMapper userMapper;
    @Autowired
    primeUtil primeUtil;


    /***
     * 获取用户列表
     * mwr dei
     * @return
     */
    public Map<String,Object> getUserList(String key,Integer status,Integer vip,Integer page){
        Map<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try {
            //获取请求数据
            if(page<1){
                data.put("totalPage", new Integer("0"));
                response.put("data",data);
                Result result = new Result("Invalid Page");
                response.put("result",result);
                return response;
            }
            page = (page -1)*10;
            String keys = key==""? null:key;
            //处理请求key字段
            String nickname = keys;
            String telephone = keys;
//            if(keys!=null) {
//                if (keys.length == 1) {
//                    if (primeUtil.checkPhone(keys[0])) {
//                        telephone = keys[0];
//                    } else {
//                        nickname = keys[0];
//                    }
//                } else if (keys.length == 2) {
//                    if (primeUtil.checkPhone(keys[0])) {
//                        telephone = keys[0];
//                        nickname = keys[1];
//                    } else if (primeUtil.checkPhone(keys[1])) {
//                        telephone = keys[1];
//                        nickname = keys[0];
//                    } else {
//                        nickname = keys[0] + keys[1];
//                    }
//                } else {
//                    nickname = keys.toString();
//                }
//            }
            List<UserEntity> preusers = userMapper.queryUser(telephone, nickname, status, vip, page, PAGE_SIZE);
            List< UserBO>  users = UserBO.transToVo(preusers);
            Integer totalPage = (userMapper.queryUserNum(telephone,nickname,status,vip)/PAGE_SIZE)+1;
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
     * mwr dei
     * @param req
     * @return
     */
    public Map<String,Result> changeUserState(Map<String,Object> req)  {
        Long userId = (Long) req.get("id");
        HashMap<String,Result> response = new HashMap<>();
        //不合法则删除
        if(userId==null){
            Result result = new Result("Invalid user id");
            response.put("result",result);
            return response;
        }
        try {
                Integer state = (Integer) req.get("status");
                Integer vip = (Integer) req.get("vip");
                int stateAns = state!=null? userMapper.updateUserState(Long.valueOf(userId), state):0;
                int vipAns = vip!=null ?userMapper.updateUserVip(Long.valueOf(userId), vip):0;
                if (vipAns == 1 || stateAns == 1) {
                    Result result = new Result();
                    response.put("result", result);
                } else {
                    Result result = new Result("Database error");
                    response.put("result", result);
                }
        }catch (Exception e){
            //处理异常
            Result result = new Result(e.getMessage());
            response.put("result",result);
            return response;
        }
        return response;
    }

}
