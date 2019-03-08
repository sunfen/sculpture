package cn.sf.sculpture.project.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.project.domain.PrincipalDTO;
import cn.sf.sculpture.project.domain.PrincipalListDTO;
import cn.sf.sculpture.project.domain.ProjectPrincipalSummary;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.PrincipalRepository;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.util.ProjectConvert;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class PrincipalServiceImpl implements PrincipalService {
    
    @Autowired
    private ProjectConvert projectConvert;
    @Autowired
    private UserService userService;
    @Autowired
    private PrincipalRepository repository;
    
    private static String[] LETTER = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  "W",  "X",  "Y", "Z"};
    
    @Override
    @Transactional
    public Principal insert(final Principal principal) {
        Assert.notNull(principal, "Principal is null");
        
        final User user = userService.findCurrentUser();
        Principal entity = repository.findByUserAndName(user, principal.getName().trim());
        if(entity != null) {
            return entity;
        }
        
        entity = new Principal();
        
        entity.setName(principal.getName().trim());
        entity.setPhone(principal.getPhone());
        entity.setUser(user);
        
        return repository.save(entity);
         
    }

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.PrincipalService#insert(cn.sf.sculpture.project.domain.PrincipalDTO)
     */
    @Override
    @Transactional
    public void insert(PrincipalDTO principal) {
        Assert.notNull(principal, "Principal is null");
       
        Principal entity = null;
        
        if(principal.getId() != null) {
            entity = repository.getOne(principal.getId());
       
        }else {
            
            entity = new Principal();
            entity.setUser(userService.findCurrentUser());
        }
         
        entity.setName(principal.getName());
        
        repository.save(entity);
         
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
             
             List<ProjectSummary> projectSummaries = projectConvert.convertSummary(projects);
             
             summary.setTotal(projects.size());
             summary.setProjects(projectSummaries);
             summaries.add(summary);
         }
             
         return new PageImpl<>(summaries, pageable, principals.getTotalElements());
    }



    
    @Override
    public List<PrincipalListDTO> getList() throws BadHanyuPinyinOutputFormatCombination{
       
       
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        
        final User user = userService.findCurrentUser();
        final List<Principal> list = repository.findByUser(user);
        
        Map<String, List<PrincipalDTO>> sets = new HashMap<>();

        for(String letter : LETTER) {
            
            sets.put(letter, new ArrayList<>());
        }
        
        for(final Principal principal : list) {
            String key = null;
            String name = principal.getName();
            char[] arr = name.toCharArray();
            if(arr == null || arr.length == 0) {
                return null; 
            }
            char firstName = arr[0];
            
            if (firstName > 128) { //如果已经是字母就不用转换了
                //获取当前汉字的全拼
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(firstName, defaultFormat);
                if (temp != null) {
                    firstName = temp[0].charAt(0);// 取首字母  
                }
            } else if (firstName >= 'a' && firstName <= 'z') {
                 
                firstName -= 32;
            }else {
                firstName = new String("#").toCharArray()[0];
            }
            key = new StringBuffer().append(firstName).toString().toUpperCase();
           
            if(key != null && key != ""){
                List<PrincipalDTO> values = sets.get(key);
                values.add(new PrincipalDTO(principal.getId(), principal.getName(), principal.getProjects().size()));
                sets.put(key, values);
            }
        }
        List<PrincipalListDTO> results = new ArrayList<>();
        int i = 0;
      
        for(String key : sets.keySet()) {
            results.add(new PrincipalListDTO(i, key, sets.get(key)));
            i ++;
        }
        results.add(new PrincipalListDTO(i, "", new ArrayList<>()));
        return results;
    }



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.PrincipalService#countByUser(cn.sf.sculpture.user.domain.entity.User)
     */
    @Override
    public Integer countByUser(User user) {
         return repository.countByUser(user);
    }

}

