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
public class RegisterRequestVO implements ViewObject {

    private String nickname;
    private String pwd;
    private String telephone;
    private String code;
    
}
