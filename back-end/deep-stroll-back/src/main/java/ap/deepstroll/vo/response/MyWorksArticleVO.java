package ap.deepstroll.vo.response;

import ap.deepstroll.bo.MyArticleBO;
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
public class MyWorksArticleVO implements ViewObject {

    private MyArticleBO[] articles;
    private Integer totalPage;
    
}
