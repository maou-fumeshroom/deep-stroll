package ap.deepstroll.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @JsonIgnore
    private List<BackMenuBO> sonMenuList;

    public void addSonMenuList(BackMenuBO backMenuBO) {
        this.sonMenuList.add(backMenuBO);
    }

    public void tranArray() {
        if (this.sonMenuList.size() > 0) {
            this.sonMenu = sonMenuList.toArray(new BackMenuBO[sonMenuList.size()]);
        } else {
            this.sonMenu = null;
        }
    }

}
