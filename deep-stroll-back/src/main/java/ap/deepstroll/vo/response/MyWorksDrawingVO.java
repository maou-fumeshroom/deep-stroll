package ap.deepstroll.vo.response;

import ap.deepstroll.bo.MyDrawingBO;
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
public class MyWorksDrawingVO implements ViewObject {

    private MyDrawingBO[] drawing;
    private Integer totalPage;
    
}
