package ap.deepstroll.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class HttpRequestUtil {

    protected String sendHttpRequest(String url, String param){
        String result = "";
        String urlName = url + "?" + param;
        try {
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有的响应头字段
            // Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            // System.out.println(key + "-->" + map.get(key));
            // }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常" + e);
            e.printStackTrace();
            return "";
        }
        return result;
    }

}
