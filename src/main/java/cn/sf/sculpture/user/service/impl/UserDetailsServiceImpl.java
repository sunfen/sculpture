package cn.sf.sculpture.user.service.impl;



import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.repository.UserRepository;



@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userReposity;
    
    /**
     * 加载登录的user的权限
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

    	final User user = userReposity.findByOpenidOrPhone(username, username);

        if (user != null) {

            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new LinkedList<SimpleGrantedAuthority>());
        }

        throw new UsernameNotFoundException("User: " + username + " not found.");
    }
}
