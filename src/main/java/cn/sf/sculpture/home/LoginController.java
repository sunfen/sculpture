 package cn.sf.sculpture.home;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.HttpState;
import cn.sf.sculpture.user.domain.UserDTO;
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
    
    /**
     * 登录
     * @param password
     * @param username
     * @return
     */
    @PostMapping
    @ResponseBody
    public HttpState<Serializable> loginWechat(@RequestBody UserDTO user) {
        
        userService.insert(user);

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getOpenid(), user.getOpenid());
    
        // 执行认证登陆
        subject.login(token);
        
        return HttpState.success(subject.getSession().getId());
    }

}
