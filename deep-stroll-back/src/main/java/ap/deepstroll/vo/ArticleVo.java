package ap.deepstroll.vo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleVo {
    private Long id;
    private Long authorId;
    private String title;
    private String cover;
    private String introduction;
    private String url;
    private String labels;
    private Integer classifyId;
    private Integer likeNum;
    private Integer state;
    private Timestamp createTime;
    private Timestamp updateTIme;
}
