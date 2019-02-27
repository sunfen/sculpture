package cn.sf.sculpture.project.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.project.domain.ProjectPrincipalSummary;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.PrincipalRepository;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class PrincipalServiceImpl implements PrincipalService {
    
    
    @Autowired
    private UserService userService;
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
        entity.setUser(userService.findCurrentUser());
        
        return repository.save(entity);
         
    }
    


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.PrincipalService#delete(java.lang.Long)
     */
    @Override
    @Transactional
    public void delete(Long id) {
        Principal principal = repository.getOne(id);
        if(principal != null) {
            repository.delete(principal);
        }
    }
    
   
    
    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAllByPrincipal()
     */
    @Override
    public Page<ProjectPrincipalSummary> findAllByPrincipal(Pageable pageable) {
        
         final User user = userService.findCurrentUser();
         final Page<Principal> principals = repository.findByUser(user, pageable);
         
         List<ProjectPrincipalSummary>  summaries = new ArrayList<>();
         
         for(final Principal principal : principals.getContent()) {
             ProjectPrincipalSummary summary = new ProjectPrincipalSummary();
             
             summary.setId(principal.getId());
             summary.setPrincipal(principal.getName());
             
             final List<Project> projects = principal.getProjects();
             
             summary.setTotal(projects.size());
             
             
             List<ProjectSummary> projectSummaries = new ArrayList<>();
             
             for(final Project project : projects) {
                 
                 ProjectSummary pro = new ProjectSummary();
                 pro.setId(project.getId());
                 pro.setName(project.getName());
                 pro.setStartTime(project.getStartTime());
                 projectSummaries.add(pro);
             }
             
             summary.setProjects(projectSummaries);
             
             summaries.add(summary);
         }
             
         return new PageImpl<>(summaries, pageable, principals.getTotalElements());
    }







}
