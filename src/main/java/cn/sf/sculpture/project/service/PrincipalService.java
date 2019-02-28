package cn.sf.sculpture.project.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.project.domain.PrincipalListDTO;
import cn.sf.sculpture.project.domain.ProjectPrincipalSummary;
import cn.sf.sculpture.project.domain.entity.Principal;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

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


    /**
     * 通讯录
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination 
     */
    List<PrincipalListDTO> getList() throws BadHanyuPinyinOutputFormatCombination;
}
