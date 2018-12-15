package cn.sf.sculpture.user.service;

import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.domain.entity.User;

public interface UserService {

    /**
     * 微信新增
     * @param user
     * @return 
     */
    User insertByWechat(UserDTO user);
    
    /**
     * 查询
     * @param openid
     */
    User findByOpenid(String openid);
}
