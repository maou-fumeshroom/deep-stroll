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
public class Result {

    private Integer code = 1;
    private String message;

    public Result(String err) {
        this.code = 0;
        this.message = err;
    }

}
