package ap.deepstroll.vo.response;

import ap.deepstroll.bo.ClassifyAdminBO;
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
@ToString
@Builder
public class GetClassifyAdminVO implements ViewObject {
    
    private ClassifyAdminBO[] classifys;
    private Integer totalPage;
}
