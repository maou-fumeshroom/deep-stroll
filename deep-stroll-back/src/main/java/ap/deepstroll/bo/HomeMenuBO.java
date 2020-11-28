package ap.deepstroll.bo;

import ap.deepstroll.entity.HomeMenuEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HomeMenuBO implements BusinessObject {

    private Integer id;
    private String name;
    private String path;
    private  Integer state;

    public static List<HomeMenuBO> transMenuEntityToVo(List<HomeMenuEntity> homeMenuEntities){
        ArrayList<HomeMenuBO> boList  = new ArrayList<>();
        for(HomeMenuEntity he:homeMenuEntities){
            HomeMenuBO bo = HomeMenuBO.builder().id(he.getId())
                                                .name(he.getName())
                                                .path(he.getPath())
                                                .state(he.getState()).build();
            boList.add(bo);
        }
        return boList;
    }
}
