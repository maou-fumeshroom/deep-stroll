package ap.deepstroll.global;

import java.util.Map;
import java.util.TreeMap;

public class VerificationCodeMap {

    private Map<String, VerificationCode> map = new TreeMap<String, VerificationCode>();
    private static VerificationCodeMap instance = new VerificationCodeMap();

    private VerificationCodeMap (){}  
    public static VerificationCodeMap getInstance() {  
        return instance;  
    }
    
    public void put(String telephone, String code) {
        map.put(telephone, new VerificationCode(code));
    }

    public VerificationCode get(String telephone) {
        return map.get(telephone);
    }

    public void remove(String telephone) {
        map.remove(telephone);
    }
    
}
