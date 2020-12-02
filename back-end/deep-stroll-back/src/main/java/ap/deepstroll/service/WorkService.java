package ap.deepstroll.service;

import ap.deepstroll.vo.request.WorkVO;
import ap.deepstroll.vo.response.ResponseVO;

import org.springframework.stereotype.Service;

import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.Work;

import java.util.List;
import java.util.Map;

@Service("workService")
public  abstract class WorkService {


    private static final int ARTICLE = 0;
    private static final int  DRAWING= 1;

//    /**
//     * mwr
//     * 添加收藏
//     * @param req
//     * @return
//     */
//    public Result addCollection(Map<String,String> req){
//
//        switch (Integer.valueOf(req.get("type"))){
//            case 0: return new DrawingService().Collection(req);
//            case 1: return new ArticleService().Collection(req);
//        }
//        return new Result();
//    }

//
//    /**
//     * 删除我的作品
//     * @param req
//     * @return
//     */
//    public Result deleteWork(Map req){
//        return null;
//    }

//    /***
//     * 浏览
//     * @return
//     */
//    public  abstract  Map<String,Object>  browseWork();

    /**
     * 分类浏览用户分享的作品
     * @param
     * @return
     */
    public abstract ResponseVO searchWork(String title,String labels,Integer classifyId,Integer state,Integer page);

    /**
     * 获取作品的分类
     * @return
     */
    public Map<String,Object> getClassify(){return null;};

    /**
     *  获取作品详情
     */
    public abstract ResponseVO getDetail(Long id);

    /**
     * 发布文章
     * @param
     * @return
     */
    public  abstract Map<String,Result> Publish(WorkVO work,long id);

}
