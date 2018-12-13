package cn.sf.sculpture.common;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/08/28
 */
@Component
public class SendSmsUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${emms.sms.url}")
    private String smsUrl;
    
    @Value("${emms.sms.uId}")
    private String smsUid;
    
    @Value("${emms.sms.key}")
    private String smsKey;
    
    private final StringBuffer path = new StringBuffer();

    @PostConstruct
    private void init() {
        this.restTemplate.getMessageConverters().add(0, 
            new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }
    
    /**
     * 发送短信
     * @param phones 手机号码
     * @param msg 短信内容
     * @return 发送是否成功，key：手机号，val：发送状态(ok：成功，其他都是失败)
     */
    public Map<String, HttpStatus> sendSMS(final Set<String> phones,final String msg) {
        if(phones == null || phones.isEmpty() || msg == null || msg.isEmpty()) {
            return null;
        }
        final Map<String, HttpStatus> result = new HashMap<>(phones.size());
        try {
            final URI uri = new URI(this.getSendSMSURL(phones, msg));
            final ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(uri, String.class);
            for (final String phone : phones) {
                result.put(phone, responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            for (final String phone : phones) {
                result.put(phone, HttpStatus.CONFLICT);
            }
        }
        return result;
    }
    
    private final String getSendSMSURL(final Set<String> phones, final String msg) {
        if(this.path.length() == 0) {
            this.path.append(this.smsUrl);
            this.path.append("?userid=");
            this.path.append(this.smsUid);
            this.path.append("&password=");
            this.path.append(this.smsKey);
            this.path.append("&pszMobis=");
        }
        StringBuffer path = new StringBuffer(this.path);
        phones.forEach(phone -> path.append(phone + ","));
        path.deleteCharAt(path.length() - 1);
        path.append("&pszMsg=");
        path.append(msg);
        path.append("&iMobiCount=" + phones.size());
        path.append("&pszSubPort=*");
        return path.toString();
    }

}
