package cn.sf.sculpture.project.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.repository.PrincipalRepository;
import cn.sf.sculpture.project.service.PrincipalService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private PrincipalRepository repository;
    
    @Override
    @Transactional
    public Principal insert(final Principal principal) {
        Assert.notNull(principal, "Principal is null");
        
        Principal entity = new Principal();
        
        entity.setDeleted(false);
        entity.setName(principal.getName());
        entity.setPhone(principal.getPhone());
        return repository.save(entity);
         
    }



}
