package ap.deepstroll.service;

import ap.deepstroll.bo.Result;
import ap.deepstroll.bo.ThemeListBO;
import ap.deepstroll.entity.ThemeEntity;
import ap.deepstroll.mapper.ThemeMapper;
import ap.deepstroll.vo.response.ResponseVO;
import ap.deepstroll.vo.response.ThemeListVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("adminThemeService")
public class AdminThemeService {
    @Autowired
    ThemeMapper themeMapper;

    /**
     * 获取主题列表
     * @return
     */
    public ResponseVO browserThemeList(){
        try {
            List<ThemeEntity> themeEntities = themeMapper.queryTheme();
            List<ThemeListBO> themeListBOs = new ArrayList<ThemeListBO>();
            for (ThemeEntity themeEntity: themeEntities) {
                themeListBOs.add(
                    ThemeListBO.builder()
                               .id(themeEntity.getId())
                               .name(themeEntity.getName())
                               .bg(themeEntity.getBackgroundUrl())
                               .bgm(themeEntity.getBgmUrl())
                               .isdefault(themeEntity.getIsDefault())
                               .build()
                );
            }
            ThemeListVO vo = ThemeListVO.builder().themes(themeListBOs.toArray(new ThemeListBO[themeListBOs.size()])).build();
            return ResponseVO.builder()
                             .data(vo)
                             .result(new Result())
                             .build();
        } catch(Exception e) {
            return ResponseVO.builder()
                             .result(new Result("server error"))
                             .build();
        }
    }

    /***
     * 删除指定主题
     * @param id
     * @return
     */
    public Map<String ,Result> deleteThemeById(Integer id){
        HashMap<String,Result> response = new HashMap<>();
        try {
            themeMapper.deleteThemeById(id);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /***
     * 修改默认主题
     * @param id
     * @return
     */
    public Map<String, Result> setDefaultTheme(Integer id){
        HashMap<String,Result> response = new HashMap<>();
        try {
            themeMapper.setDefaultTheme(id);
            Result result = new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result = new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

    /***
     * 根据id获得主题
     */
//    public Map<String,Object> queryThemeById(Integer id){
//        HashMap<String,Object> response = new HashMap<>();
//        try {
//            ThemeEntity theme = themeMapper.queryThemeById(id);
//            response.put("data",theme);
//            response.put("result",new Result());
//        }catch (Exception e){
//            response.put("data",null);
//            response.put("result",new Result(e.getMessage()));
//        }
//        return response;
//    }

    /***
     * 管理员添加主题
     * 还没加log
     * @param
     * @return
     */
    public Map<String, Result> insertNewTheme(String name, String bg, String bgm){
        HashMap<String,Result> response = new HashMap<>();
        try {
            ThemeEntity themeEntity = new ThemeEntity();
            themeEntity.setName(name);
            themeEntity.setBackgroundUrl(bg);
            themeEntity.setBgmUrl(bgm);
            themeMapper.insertNewTheme(themeEntity);
            Result result=new Result();
            response.put("result",result);
        }catch (Exception e){
            Result result=new Result(e.getMessage());
            response.put("result",result);
        }
        return response;
    }

}
