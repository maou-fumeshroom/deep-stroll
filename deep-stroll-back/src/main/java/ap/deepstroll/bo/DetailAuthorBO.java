package ap.deepstroll.bo;

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
public class DetailAuthorBO implements BusinessObject {

    private Long id;
    private String nickname;
    private String avatar;
    
}
