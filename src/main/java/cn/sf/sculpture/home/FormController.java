package cn.sf.sculpture.home;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sf.sculpture.common.Form;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/form")
public class FormController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @PostMapping
    public void fromId(@RequestBody Form form) throws Exception{
        
        if(form == null || form.getOpenId() == null || form.getFormId() == null) {
            return;
        }
        
        final String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(form.getOpenId() + uuid, form.getFormId(), 7, TimeUnit.DAYS);
    }
}
