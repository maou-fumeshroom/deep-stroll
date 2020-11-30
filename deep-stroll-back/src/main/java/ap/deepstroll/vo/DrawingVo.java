package ap.deepstroll.vo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DrawingVo {
    private Long id;
    //    private Long authorId;
    private String title;
    private String cover;
    private String introduction;
    private String avatar;
    private String nickName;
    //    private String url;
//    private String labels;
//    private Integer classifyId;
    private Timestamp dateTime;
    private String comment;
    private Integer likeNum;
    private Integer status;
    private String classifyName;
//    private Timestamp createTime;
//    private Timestamp updateTIme;
}
