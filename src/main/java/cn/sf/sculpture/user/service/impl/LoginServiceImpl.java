package cn.sf.sculpture.user.service.impl;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.sf.sculpture.user.domain.UserDTO;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.LoginService;
import cn.sf.sculpture.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    
    private SessionRegistry sessionRegistry;
    
    private RestTemplate restTemplate = new RestTemplate();
    
    @Value("${sculpture.url}")
    private String URL;
    
    
    /** (non-Javadoc)
     * @return 
     * @return 
     * @see cn.sf.sculpture.user.service.LoginService#wechatLogin(cn.sf.sculpture.user.domain.UserDTO)
     */
    @Override
    public String wechatLogin(UserDTO userDTO) {
         Assert.notNull(userDTO, "userDTO is null");
         Assert.notNull(userDTO.getOpenid(), "userDTO is null");

         User user = userService.findByOpenid(userDTO.getOpenid());
         
         //insert user
         if(user == null) {
            user = userService.insertByWechat(userDTO);
         }

        return this.postLogin(user.getOpenid());
         
    }

    
    
    private String postLogin(final String openid) {
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
       
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", openid);
        map.add("password", openid);
      
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
       
        try {
            final URI uri = new URI(URL + "login");
            this.restTemplate.postForEntity(uri, request, String.class);
       
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
        return sessions.get(0).getSessionId();
    }


}
