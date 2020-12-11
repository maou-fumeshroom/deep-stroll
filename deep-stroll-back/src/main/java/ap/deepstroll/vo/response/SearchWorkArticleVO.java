package ap.deepstroll.vo.response;


import ap.deepstroll.bo.SearchArticalBO;
import ap.deepstroll.vo.ViewObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SearchWorkArticleVO implements ViewObject {

    private SearchArticalBO[] articles;
    private Integer totalPage;
    
}
