package ap.deepstroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.UserBO;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.ArticleMapper;
import ap.deepstroll.mapper.DrawingMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.vo.request.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ap.deepstroll.entity.ArticleEntity;
import ap.deepstroll.entity.DrawingEntity;
import ap.deepstroll.entity.UserEntity;
import ap.deepstroll.mapper.ArticleMapper;
import ap.deepstroll.mapper.DrawingMapper;
import ap.deepstroll.mapper.UserMapper;
import ap.deepstroll.vo.request.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    DrawingMapper drawingMapper;

    /**
     * 修改用户信息
     * @author lqy
     * @param
     * @return
     */
      public Result updateUser(UserVo req,long id){

          UserEntity userEntity = UserEntity.builder().id(id)
                                                        .eMail(req.getEmail()==""? null:req.getEmail())
                                                        .nickname(req.getNickName()==""? null:req.getNickName())
                                                        .gender(req.getSex())
                                                        .sign(req.getSign()==""?null:req.getSign())
                                                        .avatar(req.getAvatar()==""? null:req.getAvatar()).build();
          try {
              userMapper.updateUser(userEntity);
              Result result = new Result();
              return result;
          }catch (Exception e){
              e.printStackTrace();
              Result result = new Result(e.getMessage());
              return result;
          }
    }

    /**
     * 获取用户基本信息
     * nickname & avatar vip
     * @param
     * @return
     */
    public Map<String,Object> getUserBasicInfo(Long id){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try{
            UserEntity userEntity = userMapper.queryUserById(id);
            data.put("nickename",userEntity.getNickname());
            data.put("avatar",userEntity.getAvatar());
            data.put("vip",userEntity.getVip());
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /***
     * 返回指定id的用户全部信息
     * @param id
     * @return
     */
    public Map<String,Object> queryUserAllInfoById(Long id){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try{
            UserEntity userEntity = userMapper.queryUserById(id);
            data.put("nickname",userEntity.getNickname());
            data.put("sex",userEntity.getGender());
            data.put("telephone",userEntity.getTelephone());
            data.put("email",userEntity.getEMail());
            data.put("sign",userEntity.getSign());
            data.put("avatar",userEntity.getAvatar());
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /***
     * 返回指定id用户信息:
     * @param id
     * @return
     */
    public Map<String,Object> queryUserInfoById(Long id){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> data = new HashMap<>();
        try{
            UserEntity userEntity = userMapper.queryUserById(id);
            data.put("nickname",userEntity.getNickname());
            data.put("avatar",userEntity.getAvatar());
            List<ArticleEntity> articleEntityList = articleMapper.queryArticleByAuthorId(id,null,null,null,0,0,10);
            data.put("article",articleEntityList.size());
            List<DrawingEntity> drawingEntityList = drawingMapper.queryDrawingByAuthorId(id,null,null,null,0,0,10);
            data.put("drawing",drawingEntityList.size());
            Integer likeNum = articleMapper.queryArticleNumByAuthorId(id,null,null,null,0);
            likeNum += drawingMapper.queryDrawingNumByAuthorId(id,null,null,null,0);
            data.put("likeNum",likeNum);
            data.put("sign",userEntity.getSign());
            response.put("data",data);
            response.put("result",new Result());
        }catch (Exception e){
            e.printStackTrace();
            response.put("data",null);
            response.put("result",new Result(e.getMessage()));
        }
        return response;
    }

    /**
     * 冻结用户:需要log
     * @param
     * @return
     */
//    public String  updateUserState(Long id, Integer state){
//        return userMapper.updateUserState(id,state).toString();
//    }

}
