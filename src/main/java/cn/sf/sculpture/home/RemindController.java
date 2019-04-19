package cn.sf.sculpture.home;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.common.MiniprogramResult;
import cn.sf.sculpture.common.RemindMessage;
import cn.sf.sculpture.common.RemindMessageData;
import cn.sf.sculpture.common.TemplateMessage;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
public class RemindController {

    private Log logger = LogFactory.getLog(RemindController.class);

    private static final String APPID = "wx39dc7970f2861ede";
    private static final String SECRET = "b105e5b8e1cf7d321119ace33f89ebd2";
    
    Gson gson = new Gson();

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private LogRecordService logRecordService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    /**
     * 消息
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Scheduled(cron = "0 0 20 * * ?" ) 
    public void getProcessRequest() throws Exception{
        String accessTokenResult = 
            restTemplate.getForObject(
                String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", APPID, SECRET), String.class);
       
        @SuppressWarnings("unchecked")
        final Map<String, String> result = gson.fromJson(accessTokenResult, Map.class);

        if(result != null && result.get("access_token") != null) {
        
            acceptMessage(result.get("access_token"));
        }
    }

    
    /**
     * 聊天处理方法
     * @param request
     * @param response
     * @throws Exception 
     */
    public void acceptMessage(String access_token) throws Exception{
    
            //获取到服务器中的access_tocken
            String requestUrl = 
                String.format("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=%s", access_token);
            logger.info("发送统一模板消息到:" + requestUrl);
         
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            
            // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            final LocalDate nowDate = LocalDate.now();
            final String now = CommonUtil.formatter(nowDate);
            final int year = nowDate.getYear();
            final List<User> users = userService.findAll();
            
            for(final User user : users) {
                if(user.getOpenid() == null || user.getOpenid().isEmpty()) {
                    continue;
                }
             
                final Set<String> keys = redisTemplate.keys(user.getOpenid() + "*");
                if(keys == null || keys.isEmpty()) {
                    continue;
                }
                Iterator<String> iterator = keys.iterator();
                String formIdKey = null;
                if(iterator.hasNext()) {
                    formIdKey =  iterator.next();
                }else {
                    continue;
                }
                
                final String formId = (String)redisTemplate.opsForValue().get(formIdKey);
                if(formId == null || formId.isEmpty()) {
                    continue;
                }
                redisTemplate.delete(formIdKey);
                
                final List<Map<String, Object>> counts = logRecordService.count(year);
                BigDecimal numbers = new BigDecimal(0);
                for(final Map<String, Object> count : counts) {
                    final String openid = (String)count.get("openid");
                    if(openid != null && openid.equals(user.getOpenid())) {
                        if(count.get("count") != null) {
                            numbers = (BigDecimal)count.get("count");
                        }
                        break;
                    }
                }
                
                final RemindMessageData data = new RemindMessageData(now, year, numbers);
                final TemplateMessage msg = new TemplateMessage(formId, data);
                final RemindMessage remindMsg = new RemindMessage(access_token, user.getOpenid(), msg);
              
                HttpEntity<String> requestEntity = new HttpEntity<String>(gson.toJson(remindMsg), headers);

                // 进行网络请求,访问url接口
                ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, String.class);
                logger.info("发送统一模板消息后接受数据:" + responseEntity);
                logger.info("user openid:" + user.getOpenid());
                if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
                    String sessionData = responseEntity.getBody();
                    
                    MiniprogramResult miniprogramResult = gson.fromJson(sessionData, MiniprogramResult.class);
                    
                    int errorCode = miniprogramResult.getErrcode();
                    String errorMessage = miniprogramResult.getErrmsg();
                    if (errorCode == 0) {
                        logger.error("seng message success :" + errorCode + "," + errorMessage);
                    } else {
                        logger.error("seng message fail :" + errorCode + "," + errorMessage);
                    }
                }
            }
        }
}
