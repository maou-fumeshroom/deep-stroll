package com.example.demo.Service;

import com.example.demo.Entity.Result;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("workService")
public abstract  class WorkService {


    /**
     * 删除我的作品
     * @param req
     * @return
     */
    public Result deleteWork(Map req){
        return null;
    }



}
