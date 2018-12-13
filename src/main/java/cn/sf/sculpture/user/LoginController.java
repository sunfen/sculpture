 package cn.sf.sculpture.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
