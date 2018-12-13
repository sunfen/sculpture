 package cn.sf.sculpture.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.user.domain.UserDTO;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
@Controller
@RequestMapping("login")
public class LoginController {

    /**
     * 获取微信用户基本信息
     */
    @GetMapping("wechat/{openid}")
    @ResponseBody
    public UserDTO loginWechat(@PathVariable("openid") String openid) {
        System.out.println(openid);
   
        return new UserDTO();
    }
    
    
    /**
     * 新增/编辑
     * @param openid
     * @param userDTO
     * @return
     */
    @PostMapping("wechat/{openid}")
    @ResponseBody
    public UserDTO insert(@PathVariable("openid") String openid,
        @RequestBody UserDTO userDTO) {
        System.out.println(openid);
        System.out.println(userDTO.getNickName());
        
        return new UserDTO();
    }
    
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("sendidentify/{phone}")
    @ResponseBody
    public UserDTO insert(@PathVariable String phone) {
        System.out.println(phone);
        
        return new UserDTO();
    }
    
    /**
     * 手机验证码登录
     * @param phone
     * @param identify
     * @return
     */
    @GetMapping("phone")
    @ResponseBody
    public UserDTO insert(@RequestParam String phone, @RequestParam String identify) {
        System.out.println(phone);
        System.out.println(identify);
        
        return new UserDTO();
    }
}
