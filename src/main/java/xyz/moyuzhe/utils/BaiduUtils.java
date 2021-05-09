package xyz.moyuzhe.utils;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class BaiduUtils {
    public static final String APP_ID ="24134905";

    public static final String API_KEY = "vrWzkND1Rthiq1czsCPIWGov";

    public static final String SECRET_KEY = "1pQmH24WeldTBRaKI4n0YCAhCAhqEoBB";

    /*初始化客户端*/
    public static AipContentCensor client = null;

    public BaiduUtils(){
        System.out.println("初始化Baidu");
        client = new AipContentCensor(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public JSONObject checkImg(MultipartFile files){
        byte[] filesBytes = new byte[0];
        try {
            filesBytes = files.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return client.imageCensorUserDefined(filesBytes,null);
    }

    public JSONObject checkText(String text){
        return client.textCensorUserDefined(text);
    }

}
