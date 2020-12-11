package ap.deepstroll.entity;

import java.sql.Timestamp;

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
@ToString
@Builder
public class ArticleEntity extends Work {

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
    private Timestamp updateTime;

}
