 package cn.sf.sculpture.home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.sf.sculpture.message.AccessTokenResult;
import cn.sf.sculpture.message.AesException;
import cn.sf.sculpture.message.Image;
import cn.sf.sculpture.message.Link;
import cn.sf.sculpture.message.Miniprogrampage;
import cn.sf.sculpture.message.MsgtypeEnum;
import cn.sf.sculpture.message.ReceiveMessage;
import cn.sf.sculpture.message.SHA1;
import cn.sf.sculpture.message.SendMessage;
import cn.sf.sculpture.message.TextContent;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    private static final String TOKEN = "sunfen1993sf";
    
    private static final String APPID = "wx39dc7970f2861ede";
    private static final String SECRET = "b105e5b8e1cf7d321119ace33f89ebd2";
    @Autowired
    private RestTemplate restTemplate;
    
    private Log logger = LogFactory.getLog(MessageController.class);
    
    
    /**
     * 消息
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping("/token")
    @ResponseBody
    public String getProcessRequest( 
        @RequestParam(name="signature", required = false)String signature,
        @RequestParam(name="timestamp", required = false)String timestamp,
        @RequestParam(name="nonce", required = false)String nonce,
        @RequestParam(name="echostr", required = false)String echostr, 
        HttpServletRequest request) throws Exception{
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        
        if(isGet){//首次验证token
            return isProcessRequest(signature, timestamp, nonce, echostr);
        }else{
            // 接收消息并返回消息   进入POST聊天处理  
            acceptMessage(request);  
        }
        return null;
    }


    /**
     * 验证消息推送Token
     * @return 
     * @throws AesException 
     */
    public String isProcessRequest(String signature, String timestamp,
        String nonce, String echostr) throws AesException{
    
        List<String> params = new ArrayList<String>();  
        params.add(TOKEN);  
        params.add(timestamp);  
        params.add(nonce);  
        
        // 1. 将token、timestamp、nonce三个参数进行字典序排序  
        Collections.sort(params, new Comparator<String>() {  
            @Override  
            public int compare(String o1, String o2) {  
                return o1.compareTo(o2);  
            }  
        }); 
        
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密  
        String temp = SHA1.getSHA1(params.get(0), params.get(1), params.get(2));  
        if (temp.equals(signature)) {
            return echostr;
        }  
        return null;   
    }

    
    /**
     * 聊天处理方法
     * @param request
     * @param response
     * @throws Exception 
     */
    public void acceptMessage(HttpServletRequest request) throws Exception{
    
        //  这是通过通过get方式去url 拼接的键值对，post方式取不到值
        request.setCharacterEncoding("UTF-8");         //返回页面防止出现中文乱码
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));//post方式传递读取字符流
        StringBuilder result = new StringBuilder();
       
        String jsonStr;
        try {
            while (( jsonStr = reader.readLine()) != null) {
                result.append(jsonStr);
            }
        } catch (IOException e) {
        
            e.printStackTrace();
        }
        reader.close();// 关闭输入流
       
        logger.info("result" + result);
        Gson gson = new Gson();
        final ReceiveMessage receiveMessage = gson.fromJson(result.toString(), ReceiveMessage.class);
        logger.info("message" + receiveMessage);
        logger.info("getFromUserName" + receiveMessage.getFromUserName());
        logger.info("getToUserName" + receiveMessage.getToUserName());
        logger.info("getContent" + receiveMessage.getContent());
        logger.info("getMsgType" + receiveMessage.getMsgType());
        logger.info("getPicUrl" + receiveMessage.getPicUrl());
        logger.info("getSessionFrom" + receiveMessage.getSessionFrom());
        logger.info("getAppId" + receiveMessage.getAppId());
        logger.info("getCreateTime" + receiveMessage.getCreateTime());
        logger.info("getEvent" + receiveMessage.getEvent());
        logger.info("getMediaId" + receiveMessage.getMediaId());
        logger.info("getMsgId" + receiveMessage.getMsgId());
        logger.info("getPagePath" + receiveMessage.getPagePath());
        logger.info("getThumbMediaId" + receiveMessage.getThumbMediaId());
        logger.info("getThumbUrl" + receiveMessage.getThumbUrl());
        logger.info("getTitle" + receiveMessage.getTitle());
        
        String token = this.getToken();
        
        SendMessage sendMessage = new SendMessage();
        sendMessage.setAccess_token(token);
        sendMessage.setTouser(receiveMessage.getFromUserName());
        sendMessage.setMsgtype(receiveMessage.getMsgType());
        final MsgtypeEnum msgType = MsgtypeEnum.valueOf(receiveMessage.getMsgType());
        if(msgType.equals(MsgtypeEnum.event)) {
            return;
        }
        switch (msgType) {
            
            case text:
                sendMessage.setText(new TextContent(receiveMessage.getContent()));
                break;
            
            case image:
            
                sendMessage.setImage(new Image(receiveMessage.getMediaId()));
            break;
          
            case link:
                Link link = new Link();
                
                link.setTitle(receiveMessage.getTitle());
                link.setDescription(receiveMessage.getTitle());
                link.setThumb_url(receiveMessage.getMediaId());
                link.setUrl(receiveMessage.getPicUrl());
                
                sendMessage.setLink(link);
                
                break;
            case miniprogrampage:
                
                Miniprogrampage miniprogrampage = new Miniprogrampage();
                
                miniprogrampage.setTitle(receiveMessage.getTitle());
                miniprogrampage.setPagepath(receiveMessage.getPagePath());
                miniprogrampage.setThumb_media_id(receiveMessage.getThumbMediaId());
                
                sendMessage.setMiniprogrampage(miniprogrampage);
                
                break;
            default:
                
                break;
        }
        
        jsonStr = gson.toJson(sendMessage);
        logger.info("send" + jsonStr);
        PrintWriter out=null;
        BufferedReader in=null;
        try {
            URL url1 = new URL("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token);

            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setDoOutput(true);

            connection.connect();

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            writer.write(jsonStr);
            writer.flush();
  
            InputStreamReader reader1 = new InputStreamReader(connection.getInputStream(),"UTF-8");
            BufferedReader breader = new BufferedReader(reader1);

            StringBuffer strb = new StringBuffer();
            String str = null;

            while (null != (str = breader.readLine())) {
                strb.append(str);
            }

            logger.info("error"+ strb);
            writer.close();

            reader.close();
            breader.close();

            connection.disconnect();

        } catch (Exception e) {
            logger.info("发送post请求异常");
            e.printStackTrace();
        }finally{
            if(out!=null)out.close();
            if(in!=null)in.close();
        }
    }

    
    private String getToken() {
        
        final AccessTokenResult accessTokenResult = restTemplate.getForObject(
            String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
                APPID, SECRET)
            , AccessTokenResult.class);
    
        if( null !=accessTokenResult && accessTokenResult.getAccess_token() != null) {
        
            return accessTokenResult.getAccess_token();
        }
        
        throw new IllegalArgumentException("token is error");
    }
    
    
}
