package ap.deepstroll.vo.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleVO extends WorkVO {
    private String cover;
    private String title;
    private Integer classify;
    private String[] labels;
    private String introduction;
    private String fileUrl;

}
