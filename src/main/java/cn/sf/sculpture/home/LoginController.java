 package cn.sf.sculpture.home;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import cn.sf.sculpture.common.domain.HttpState;
import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/login")
@ResponseBody
public class LoginController {

    @Autowired
    private UserService userService;
    
    
    private static final String APPID = "wx39dc7970f2861ede";
    private static final String SECRET = "b105e5b8e1cf7d321119ace33f89ebd2";

    @Autowired
    private RestTemplate restTemplate;

    
    @GetMapping("session/{code}")
    public HttpState<Map<String, Object>> getAccessToken(@PathVariable String code) throws Exception {
    
        String accessTokenResult = 
            restTemplate.getForObject(String.format("https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&js_code=%s&appid=%s&secret=%s", code, APPID, SECRET), String.class);
       
        Gson gson = new Gson();
        @SuppressWarnings("unchecked")
        Map<String, String> result = gson.fromJson(accessTokenResult, Map.class);
        
        if(result != null && result.get("openid") != null) {
        
            return this.login(new UserDTO(result.get("openid")));
        }
        
        return null;
    }
    
    
    
    
    /**
     * 登录
     * @param password
     * @param username
     * @return
     */
    @PostMapping
    @ResponseBody
    public HttpState<Map<String, Object>> loginWechat(@RequestBody UserDTO user) {
        
        return this.login(user);
    }
    
    
    
    private HttpState<Map<String, Object>> login(UserDTO user){
        final User entity = userService.insert(user);

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(entity.getOpenid(), entity.getOpenid());
    
        // 执行认证登陆
        subject.login(token);
        Map<String, Object> map = new HashMap<>();
        map.put("session", subject.getSession().getId());
        map.put("openid", entity.getOpenid());
        map.put("avatarUrl", entity.getAvatarUrl());
        map.put("name", entity.getUsername());
        return HttpState.success(map);
    }
    

}
