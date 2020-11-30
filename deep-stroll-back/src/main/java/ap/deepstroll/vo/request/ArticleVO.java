package ap.deepstroll.vo.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ArticleVO extends WorkVO {
    private Long id;
    private String cover;
    private String title;
    private String introduction;
    private Integer likeNum;
    private String comment;
    private Integer status;
    private Integer classify;
    private String[] labels;
    private String fileUrl;

}
