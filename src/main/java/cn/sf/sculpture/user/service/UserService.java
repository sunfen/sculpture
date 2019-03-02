package cn.sf.sculpture.user.service;

import cn.sf.sculpture.project.domain.CountDTO;
import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.domain.entity.User;

public interface UserService {

    
    /**
     * 查询
     * @param openid
     */
    User findByOpenid(String openid);


    /**
     * @param username
     * @return
     */
    User insert(UserDTO user);


	User findCurrentUser();


    /**
     * @return
     */
    CountDTO count();
 
}
