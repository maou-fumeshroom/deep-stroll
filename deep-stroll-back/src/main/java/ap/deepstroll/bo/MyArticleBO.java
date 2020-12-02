package ap.deepstroll.bo;

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
@Builder
@ToString
public class MyArticleBO {
    
    private Long id;
    private String cover;
    private String title;
    private String introduction;
    private Timestamp dateTime;
    private Integer likeNum;
    private Integer comment;
    private Integer status;
}
