package ap.deepstroll.service;

import org.springframework.stereotype.Service;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.Work;

import java.util.List;
import java.util.Map;

@Service("workService")
public abstract class WorkService {


    /**
     * 删除我的作品
     * @param req
     * @return
     */
    public Result deleteWork(Map req){
        return null;
    }

    /**
     * 分类浏览用户分享的作品
     * @param req
     * @return
     */
    public  abstract  Work browseWork(Map req);

    /**
     * 获取作品的分类
     * @return
     */
    public abstract List getClassify();

    /**
     *  获取作品详情
     */
    public abstract Work getDetail(int id);

    /**
     * 发布文章
     * @param req
     * @return
     */
    public  abstract Work Publish(Map req);
}
