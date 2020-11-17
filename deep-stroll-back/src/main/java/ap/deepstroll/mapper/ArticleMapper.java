package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.ArticleEntity;

@Mapper
public interface ArticleMapper {
    
    /**
     * 插入一条新的文章
     * @author mxf
     * @param articleEntity
     */
    public Integer insertArticle(ArticleEntity articleEntity);

    /**
     * 查询指定id的文章信息
     * @author
     * @param id
     * @return
     */
    public ArticleEntity queryArticleById(@Param("id")Long id);

    /**
     * 根据限定条件搜索文章
     * @author mxf
     * @param title
     * @param label
     * @param state
     * @param startIndex notnull
     * @param pageSize notnull
     * @return
     */
    public List<ArticleEntity> queryArticleByTitleLabState(
        @Param("title")String title,
        @Param("label")String label,
        @Param("state")Integer state,
        @Param("startIndex")Integer startIndex,
        @Param("pageSize")Integer pageSize,
        @Param("likeNum")Integer likeNum
    );

    /**
     * 返回符合条件的总数
     * @author mxf
     * @param title
     * @param label
     * @param state
     * @param likeNum
     * @return
     */
    public Integer queryArticleNumByTitleLabState(
        @Param("title")String title,
        @Param("label")String label,
        @Param("state")Integer state,
        @Param("likeNum")Integer likeNum
    );

    /**
     * 更新文章状态
     * @author mxf
     * @param id
     * @param state
     */
    public Integer updateArticleState(@Param("id")Long id, @Param("state")Integer state);

}
