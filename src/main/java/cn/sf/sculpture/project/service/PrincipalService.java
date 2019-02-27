package cn.sf.sculpture.project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.project.domain.ProjectPrincipalSummary;
import cn.sf.sculpture.project.domain.entity.Principal;

public interface PrincipalService {


    /**
     * @param principal
     * @return
     */
    Principal insert(Principal principal);
 
    
    /**
     * 
     * @param id
     */
    void delete(Long id);


    /**
     * @param pageable
     * @return
     */
    Page<ProjectPrincipalSummary> findAllByPrincipal(Pageable pageable);
}
