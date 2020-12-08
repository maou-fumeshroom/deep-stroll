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
public class GetDetailArticleVO implements ViewObject {
    
    private DetailAuthorBO author;
    private String title;
    private String cover;
    private String introduction;
    private String fileUrl;
    private Integer likeNum;
    private Integer isLike;
    private Integer isCollect;
    private String[] labels;
    private Integer type = 0;
    private String dateTime;
    private Integer status;
    private String classifyName;
    
}
