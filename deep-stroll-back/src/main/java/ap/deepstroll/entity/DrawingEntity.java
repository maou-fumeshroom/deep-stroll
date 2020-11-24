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
public class DrawingEntity extends Work {

    private Long id;
    private Long authorId;
    private String title;
    private String introduction;
    private String labels;
    private String url;
    private Integer classifyId;
    private Integer state;
    private Integer likeNum;
    private Timestamp createTime;
    private Timestamp updateTime;
    
}
