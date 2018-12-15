package cn.sf.sculpture.user.service;

import cn.sf.sculpture.user.domain.UserDTO;

public interface LoginService {

    /**
     * 微信登录
     * @param user
     * @return 
     */
    String wechatLogin(UserDTO user);
}
