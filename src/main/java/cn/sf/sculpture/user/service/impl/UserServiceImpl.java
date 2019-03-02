package cn.sf.sculpture.user.service.impl;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.project.domain.CountDTO;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.service.WagesRecordService;
import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.repository.UserRepository;
import cn.sf.sculpture.user.service.UserService;
import cn.sf.sculpture.project.service.ProjectService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private PrincipalService principalService;
    @Autowired
    private WagesRecordService wagesRecordService;
    
    @Override
    public User insert(final UserDTO userDTO) {
        Assert.notNull(userDTO, "userDTO is null");
        Assert.notNull(userDTO.getOpenid(), "userDTO is null");

        User user = this.findByOpenid(userDTO.getOpenid());
        
        //insert user
        if(user == null) {
           user = this.insertByWechat(userDTO);
        }

       return user;
    }
    
    
    /** (non-Javadoc)
     * @return 
     * @see cn.sf.sculpture.user.service.UserService#insertByWechat(cn.sf.sculpture.user.domain.UserDTO)
     */
    @Transactional
    public User insertByWechat(UserDTO user) {
        Assert.notNull(user, "user is null");
        
        User entity = new User();
        
        entity.setOpenid(user.getOpenid());
        entity.setUsername(user.getUsername());
        entity.setAvatarUrl(user.getAvatarUrl());
        entity.setPassword(user.getOpenid());
        
        return repository.save(entity);
         
    }

    /** (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#findByOpenid(java.lang.String)
     */
    @Override
    public User findByOpenid(String openid) {
         
        Assert.notNull(openid, "findByOpenid openid is null");
         
        return repository.findByOpenid(openid);
    }
    
    
    /** (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#findByOpenid(java.lang.String)
     */
    @Override
    public User findCurrentUser() {

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        String openId = (String)principal;
        return repository.findByOpenid(openId);
    }


    
    /* (non-Javadoc)
     * @see cn.sf.sculpture.user.service.UserService#count()
     */
    @Override
    public CountDTO count() {
         final User user = this.findCurrentUser();
         CountDTO count = new CountDTO();
         
         count.setCountPrincipal(principalService.countByUser(user));
         
         count.setCountProject(projectService.countByUser(user));
         
         count.setCountWages(wagesRecordService.countByUser(user));
         
         
         return count;
    }
}
