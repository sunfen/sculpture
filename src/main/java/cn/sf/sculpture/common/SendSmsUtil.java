package cn.sf.sculpture.common;

import java.io.IOException;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

/**
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/08/28
 */
@Component
public class SendSmsUtil {

    @Value("${sculpture.sms.appid}")
    private int appid;
    
    @Value("${sculpture.sms.appkey}")
    private String appkey;
    
    @Value("${sculpture.sms.templateId}")
    private int templateId;


    // 需要发送短信的手机号码
    String[] phoneNumbers = {"17772607725", "17731965725", "18211039521"};

    public void sendMsg(){
        try {
            String[] params = {"5678", "10"};
            
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
         
            SmsSingleSenderResult result =
                ssender.sendWithParam("86", phoneNumbers[0], templateId, params, null, "", "");  
        
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

}
