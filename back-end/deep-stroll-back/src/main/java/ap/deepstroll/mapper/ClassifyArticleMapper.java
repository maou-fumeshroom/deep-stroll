package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.ClassifyArticleEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassifyArticleMapper {

    /**
     * 添加新的分类
     * @author mxf
     * @param classifyArticleEntity
     */
    public Integer insertClassifyArticle(@Param("name")String name);

    public Integer queryClassifyArticleByName(@Param("name")String name);

    public ClassifyArticleEntity queryClassifyById(@Param("id")Integer id);
    
    /**
     * 查询分类
     * @author mxf
     * @param state
     * @return
     */
    public List<ClassifyArticleEntity> queryAllClassifyArticle(@Param("state")Integer state);

    public List<ClassifyArticleEntity> queryClassifyArticleByPage(
        @Param("startIndex")Integer startIndex,
        @Param("pageSize")Integer pageSize,
        @Param("state")Integer state
    );

    public Integer queryClassifyNumArticleByPage(
        @Param("state")Integer state
    );
    /**
     * 修改分类状态
     * @author mxf
     * @param id
     * @param state
     */
    public Integer updateClassifyArticleState(@Param("id")Integer id, @Param("state")Integer state);

}
