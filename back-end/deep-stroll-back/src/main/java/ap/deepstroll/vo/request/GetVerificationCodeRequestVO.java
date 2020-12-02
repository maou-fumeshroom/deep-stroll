package ap.deepstroll.vo.request;

import ap.deepstroll.vo.ViewObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetVerificationCodeRequestVO implements ViewObject {

    private String telephone;
    
}
