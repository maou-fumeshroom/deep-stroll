package ap.deepstroll.Exception;

//mwr
public class StateException extends Throwable {

    private String message = null;
    public StateException(String key,String val){
        this.message = "required key "+ key +" has no value of "+ val;
    }
    public String getMessage(){
        return this.message;
    }
}
