package ap.deepstroll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ap.deepstroll.entity.ClassifyDrawingEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassifyDrawingMapper {
    
    /**
     * 插入一个新的分类
     * @author mxf
     * @param name
     * @return
     */
    public Integer insertClassifyDrawing(@Param("name")String name);

    public Integer queryClassifyDrawingByName(@Param("name")String name);

    public ClassifyDrawingEntity queryClassifyById(@Param("id")Integer id);
    
    /**
     * 获取全部分类
     * @author mxf
     * @param state
     * @return
     */
    public List<ClassifyDrawingEntity> queryAllClassifyDrawing(@Param("state")Integer state);

    public List<ClassifyDrawingEntity> queryClassifyDrawingByPage(
        @Param("startIndex")Integer startIndex,
        @Param("pageSize")Integer pageSize,
        @Param("state")Integer state
    );

    public Integer queryClassifyNumDrawingByPage(
        @Param("state")Integer state
    );

    public Integer updateClassifyDrawingState(@Param("id")Integer id, @Param("state")Integer state);
}
