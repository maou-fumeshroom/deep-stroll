package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.DrawingEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DrawingMapper {

    /**
     * 插入一条手绘
     * @author mxf
     * @param drawingEntity
     * @return
     */
    public Integer insertDrawing(DrawingEntity drawingEntity);

    /**
     * 查询指定id的手绘
     * @author mxf
     * @param id
     * @return
     */
    public DrawingEntity queryDrawingById(@Param("id")Long id);

    /**
     * 查询符合条件的手绘
     * @author mxf
     * @param title
     * @param label
     * @param classifyId
     * @param state
     * @param startIndex
     * @param pageSize
     * @param likeNum
     * @return
     */
    public List<DrawingEntity> queryDrawingByTitleLabClassifyState(
        @Param("title")String title,
        @Param("label")String label,
        @Param("classifyId")Integer classifyId,
        @Param("state")Integer state,
        @Param("startIndex")Integer startIndex,
        @Param("pageSize")Integer pageSize,
        @Param("likeNum")Integer likeNum
    );

    /**
     * 返回上个方法的符合条件的个数
     * @author mxf
     * @param title
     * @param label
     * @param classifyId
     * @param state
     * @param likeNum
     * @return
     */
    public Integer queryDrawingNumByTitleLabClassifyState(
        @Param("title")String title,
        @Param("label")String label,
        @Param("classifyId")Integer classifyId,
        @Param("state")Integer state,
        @Param("likeNum")Integer likeNum
    );

    /**
     * 更新指定手绘的状态
     * @author mxf
     * @param id
     * @param state
     * @return
     */
    public Integer updateDrawingState(@Param("id")Long id, @Param("state")Integer state);
    
    /**
     * 更新点赞数
     * @author mxf
     * @param id
     * @param num 需要添加的点赞数
     * @return
     */
    public Integer updateLikeNumById(@Param("id")Long id, @Param("num")Integer num);
}
