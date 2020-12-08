package ap.deepstroll.utils;

import org.springframework.stereotype.Component;

@Component
public class SMSMessageUtil extends HttpRequestUtil {

    public Boolean sendSMSMessage(
        String telephone,
        String code
    ){
        return true;
        // String url = "https://api.binstd.com/sms/send";
        // // todo 在这里编辑经审核的短信模板.
        // String param = "mobile=" + telephone + "&content=您的手机验证码为"+ code +"，5分钟内有效。请不要把此验证码泄露给任何人。【MBRY】&appkey=16e666a737db0889";
        // return sendHttpRequest(url, param).length() > 0;
    }
    
}
