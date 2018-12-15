 package cn.sf.sculpture.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.service.LoginService;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/login")
@ResponseBody
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    /**
     * 获取微信用户基本信息
     */
    @GetMapping("/wechat/{openid}")
    @ResponseBody
    public void loginWechat(@PathVariable("openid") String openid) {
        System.out.println(openid);
    }
    
    
    /**
     * 微信登录
     * @param userDTO
     * @return
     */
    @PostMapping("/wechat")
    @ResponseBody
    public String loginWechat(@RequestBody UserDTO userDTO) {
        
        return loginService.wechatLogin(userDTO);
    }
    
    
    
    /**
     * 手机验证码登录
     * @param phone
     * @param identify
     * @return
     */
    @PostMapping("/phone/identifycode/{phone}/{code}")
    @ResponseBody
    public String loginPhone(@RequestParam String phone, @RequestParam String code) {
        System.out.println(phone);
        System.out.println(code);
        
        return "200";
    }
    
    
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("/phone/identifycode/{phone}")
    @ResponseBody
    public String sendIdentifyCode(@PathVariable String phone) {
        System.out.println(phone);
        
        return "200";
    }

}
