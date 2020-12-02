package ap.deepstroll.vo.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DrawingVO  extends WorkVO{

    private String title;
    private Integer classify;
    private String[] labels;
    private String introduction;
    private String[] image;
}
