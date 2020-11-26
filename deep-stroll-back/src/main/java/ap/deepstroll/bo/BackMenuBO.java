package ap.deepstroll.bo;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;

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
@ToString
@Builder
public class BackMenuBO {
    
    private Integer id;
    private String name;
    private String path;
    private BackMenuBO[] sonMenu;

}
