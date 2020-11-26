package ap.deepstroll.vo;

import ap.deepstroll.bo.HomeMenuBO;
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
public class UserLogInVO implements ViewObject {

    private String token;
    private Long id;
    private HomeMenuBO[] menus;
    
}
