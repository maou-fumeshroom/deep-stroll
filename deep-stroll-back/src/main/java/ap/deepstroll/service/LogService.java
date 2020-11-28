package ap.deepstroll.service;

import ap.deepstroll.bo.LogBo;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.LogEntity;
import ap.deepstroll.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("logService")
public class LogService {

    @Autowired
    LogMapper logMapper;

    private static final Integer PAGE_SIZE = 10;

    /**
     *
     * 查看日志
     * mwr
     * @param req
     * @return
     */
    public Map<String, Object> getLogByPage(HashMap<String,Integer> req) {
        HashMap<String, Object>response = new HashMap<>();
        HashMap<String,Object>data = new HashMap<>();
        Integer page = req.get("page");
        if(page<1){//非法页数
            Result request = new Result("Invalid page");
            data.put("totalPage",new Integer("0"));
            data.put("log",null);
            response.put("data",data);
            response.put("request",request);
            return response;
        }
        try{
            page = (page-1)* PAGE_SIZE;
            List<LogEntity> preLogs = logMapper.queryLog(page,PAGE_SIZE);
            List<LogBo> logs =LogBo.transLogEntityToBo(preLogs);
            Integer totalPage = (logMapper.queryLogNum()/PAGE_SIZE)+1;
            data.put("log",logs);
            data.put("totalPage",totalPage);
            Result result = new Result();
            response.put("result",result);
            response.put("data",data);
        }catch (Exception e){
            Result request = new Result(e.getMessage());
            data.put("totalPage",new Integer("0"));
            data.put("log",null);
            response.put("data",data);
            response.put("result",request);
        }
        return response;

    }
}
