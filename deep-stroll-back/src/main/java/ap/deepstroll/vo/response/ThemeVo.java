package ap.deepstroll.vo.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ThemeVo {
    private Integer id;
    private String name;
    private String bg;
    private String bgm;
    private Integer isDefault;
}
