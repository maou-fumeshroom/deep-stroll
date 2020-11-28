package ap.deepstroll.service;

import ap.deepstroll.Exception.StateException;
import ap.deepstroll.bo.HomeMenuBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.HomeMenuEntity;
import ap.deepstroll.mapper.HomeMenuMapper;
import ap.deepstroll.util.primeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userFunctionManageService")
public class UserFunctionManageService {

    @Autowired
    HomeMenuMapper homeMenuMapper;

    @Autowired
    primeUtil primeUtil;

    /***
     * 获取功能菜单列表
     * mwr dei
     * @return
     */
    public Map<String,Object>getFunctionMenu(){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        response.put("data",data);
        try{
            List<HomeMenuEntity> preMenu = homeMenuMapper.queryMenu();
            List<HomeMenuBO> menu = HomeMenuBO.transMenuEntityToVo(preMenu);
            data.put("menu",menu);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e ){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /**
     * 设置功能状态
     * mwr dei
     * @param req
     * @return
     */
    public Map<String, Result> setFunctionState(Map<String,Integer>req){
        HashMap<String,Result> response = new HashMap<>();
        //参数错误
        if(!req.keySet().contains("id")||!req.keySet().contains("status")){
            Result result  = new Result("invalid num of param");
            response.put("result",result);
            return response;
        }
        try{
            Integer id = (Integer)req.get("id");
            Integer state= (Integer)req.get("status");
            //状态码设置错误
            if(!primeUtil.checkvalue(state,"menuStates")){
                throw  new StateException("menuState",state.toString());
            }
            int setAns = homeMenuMapper.updateMenuState(id,state);
            if (setAns==1){
                Result result = new Result();
                response.put("result",result);
            }else {
                Result result = new Result("Database Err");
                response.put("result",result);
            }
        }catch (Exception | StateException e){
            Result result  = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }
}
