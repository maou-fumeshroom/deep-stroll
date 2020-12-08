package ap.deepstroll.vo.response;

import java.sql.Timestamp;

import ap.deepstroll.bo.DetailAuthorBO;
import ap.deepstroll.vo.ViewObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetDetailDrawingVO implements ViewObject {

    private DetailAuthorBO author;
    private String title;
    private String introduction;
    private String[] images;
    private Integer likeNum;
    private Integer isLike;
    private Integer isCollect;
    private String[] labels;
    private Integer type = 1;
    private String dateTime;
    private Integer status;
    private String classifyName;
    
}
