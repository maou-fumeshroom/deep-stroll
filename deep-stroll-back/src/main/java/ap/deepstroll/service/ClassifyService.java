package ap.deepstroll.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ap.deepstroll.bo.ClassifyAdminBO;
import ap.deepstroll.bo.Result;
import ap.deepstroll.entity.ClassifyArticleEntity;
import ap.deepstroll.entity.ClassifyDrawingEntity;
import ap.deepstroll.global.Global;
import ap.deepstroll.mapper.ClassifyArticleMapper;
import ap.deepstroll.mapper.ClassifyDrawingMapper;
import ap.deepstroll.vo.request.AddClassifyAdminRequestVO;
import ap.deepstroll.vo.response.GetClassifyAdminVO;
import ap.deepstroll.vo.response.ResponseVO;

@Service
public class ClassifyService {

    @Autowired
    ClassifyArticleMapper classifyArticleMapper;

    @Autowired
    ClassifyDrawingMapper classifyDrawingMapper;

    public ResponseVO getClassify(Integer type, Integer page, Integer state) {
        Integer pageSize = Global.pageSize;
        if (type == 0) {
            List<ClassifyArticleEntity> classifyArticleEntitys = classifyArticleMapper.queryClassifyArticleByPage((page - 1) * pageSize, pageSize, state);
            List<ClassifyAdminBO> classifyAdminBOs = new ArrayList<ClassifyAdminBO>();
            for (ClassifyArticleEntity c: classifyArticleEntitys) {
                classifyAdminBOs.add(
                    ClassifyAdminBO.builder()
                                   .id(c.getId())
                                   .name(c.getName())
                                   .status(c.getState())
                                   .build()
                );
            }
            Integer totalPage = classifyArticleMapper.queryClassifyNumArticleByPage(state);
            return ResponseVO.builder()
                             .data(
                                 GetClassifyAdminVO.builder()
                                                   .classifys(classifyAdminBOs.toArray(new ClassifyAdminBO[classifyAdminBOs.size()]))
                                                   .totalPage(totalPage)
                                                   .build()
                             )
                             .result(new Result())
                             .build();
                             
        } else if (type == 1) {
            List<ClassifyDrawingEntity> classifyDrawingEntitys = classifyDrawingMapper.queryClassifyDrawingByPage((page - 1) * pageSize, pageSize, state);
            List<ClassifyAdminBO> classifyAdminBOs = new ArrayList<ClassifyAdminBO>();
            for (ClassifyDrawingEntity c: classifyDrawingEntitys) {
                classifyAdminBOs.add(
                    ClassifyAdminBO.builder()
                                   .id(c.getId())
                                   .name(c.getName())
                                   .status(c.getState())
                                   .build()
                );
            }
            Integer totalPage = classifyDrawingMapper.queryClassifyNumDrawingByPage(state);
            return ResponseVO.builder()
                             .data(
                                 GetClassifyAdminVO.builder()
                                                   .classifys(classifyAdminBOs.toArray(new ClassifyAdminBO[classifyAdminBOs.size()]))
                                                   .totalPage(totalPage)
                                                   .build()
                             )
                             .result(new Result())
                             .build();
        }
        return ResponseVO.builder()
                         .result(new Result("error type"))
                         .build();
    }

    public ResponseVO addClassify(AddClassifyAdminRequestVO request) {
        if (request.getType() == 0) {
            if (classifyArticleMapper.queryClassifyArticleByName(request.getName()) == 0) {
                classifyArticleMapper.insertClassifyArticle(request.getName());
                return ResponseVO.builder()
                                 .result(new Result())
                                 .build();
            } else {
                return ResponseVO.builder()
                         .result(new Result("名字重复"))
                         .build();
            }
        } else if(request.getType() == 1) {
            if (classifyDrawingMapper.queryClassifyDrawingByName(request.getName()) == 0) {
                classifyDrawingMapper.insertClassifyDrawing(request.getName());
                return ResponseVO.builder()
                                 .result(new Result())
                                 .build();
            } else {
                return ResponseVO.builder()
                         .result(new Result("名字重复"))
                         .build();
            }
        } else {
            return ResponseVO.builder()
                         .result(new Result("error type"))
                         .build();
        }
    }
    
}
