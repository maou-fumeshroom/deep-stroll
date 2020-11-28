package ap.deepstroll.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class primeUtil {

    private static String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(16[5,6])|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$";
    private static final Integer[] userStates = {0,1};
    private static final Integer[] vipStates = {0,1};
    private static final Integer[] menuStates = {0,1};
    private static final HashMap<String, Integer[]> statusBox= new HashMap();
    static {
        statusBox.put("userStates",userStates);
        statusBox.put("vipStates",vipStates);
        statusBox.put("menuStates",menuStates);
    }


    /***
     * 检查是不是手机号
     * mwr
     * @param phone
     * @return
     */
    public static Boolean checkPhone(String  phone) {
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
        }
        return  true;
    }


    /**
     * 判断某类型是否存在
     * mwr
     * @param i
     * @param type
     * @return
     */
    public static Boolean checkvalue(Integer i ,String type){
        Integer[] series  = ((Integer[])statusBox.get(type));
        return Arrays.asList(series).contains(i);
    }
}
