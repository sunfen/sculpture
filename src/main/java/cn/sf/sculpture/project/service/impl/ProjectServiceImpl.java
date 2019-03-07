package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.ProjectRepository;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.project.util.ProjectConvert;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectConvert projectConvert;
	@Autowired
	private UserService userService;
	@Autowired
    private PrincipalService principalService;
    @Autowired
    private ProjectRepository repository;
    
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#save(cn.sf.sculpture.project.domain.entity.Project)
     */
    @Override
    @Transactional
    public void save(Project project) {
        repository.save(project);
    }
    
    
    @Override
    @Transactional
    public void insert(final ProjectDTO project) throws Exception {
        Assert.notNull(project, "project is null");
        
        final User user = userService.findCurrentUser();
        
        Project entity = null;
        
        if(project.getId() != null) {
        	
            entity = repository.getOne(project.getId());
        
        }else { 

        	entity = new Project();
        	entity.setUser(user);
        }
        
        Principal principal = project.getPrincipal();
  
        if(principal.getId() == null) {
        	principal = principalService.insert(principal);
        }
 
        entity.setCreateTime(CommonUtil.getNow());
        entity.setAddress(project.getAddress());
        entity.setName(project.getName());
        entity.setPrincipal(principal);
        entity.setDailyWages(project.getDailyWages());

        repository.save(entity);
               
    }
    
    
    
    
    @Override
    public ProjectDTO findNew() throws Exception {
    	Project project = repository.findFirstByUserOrderByCreateTimeDesc(userService.findCurrentUser());
    	if(project == null) {
    		return null;
    	}
    	return projectConvert.convertDTO(project);
    }
    
    
    @Override
    @Transactional
    public void deleted(Long projectId) {
    	Project project = repository.getOne(projectId);
    	if(project != null) {
    		repository.delete(project);
    	}
    }
    
    

    @Override
    public Project findOne(Long projectId) {
        
        return repository.getOne(projectId);
    }



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#getOne(java.lang.Long)
     */
    @Override
    public ProjectDTO getOne(Long projectId) throws Exception {
         final Project project = repository.getOne(projectId);
         return projectConvert.convertDTO(project);
    }
    
    
    
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAll()
     */
    @Override
    public List<ProjectSummary> findAll() {

        final List<Project> results = repository.findByUserOrderByCreateTimeDesc(userService.findCurrentUser());
        
        
        return this.convertSummary(results);
    }
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAllByPrincipalId(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<ProjectSummary> findAllByPrincipalId(Long principalId, Pageable pageable) {
        final Page<Project> results = repository.findByUserAndPrincipalIdOrderByCreateTimeDesc(userService.findCurrentUser(), principalId, pageable);
        
        
        return new PageImpl<>(this.convertSummary(results.getContent()), pageable, results.getTotalElements());
    }
    
    
    
    
    private List<ProjectSummary> convertSummary(final List<Project> results){
        List<ProjectSummary> contents = new ArrayList<>();
        
        for(final Project project : results) {
            
            ProjectSummary summay = new ProjectSummary();
            
            summay.setAddress(project.getAddress());
            summay.setId(project.getId());
            summay.setName(project.getName());
            summay.setPrincipal(project.getPrincipal().getName());
            summay.setDailyWages(project.getDailyWages());
            
            final List<LogRecord> records = project.getLogRecords();
            if(!records.isEmpty()) {
                
                summay.setStartTime(records.get(0).getTime());
                summay.setEndTime(records.get(records.size()-1).getTime());
            }

            contents.add(summay);
        }
        
        return contents;
    }
    
    
    @Override
    public Page<ProjectSummary> findAll(Pageable pageable){
    	
    	final Page<Project> results = 
    	    repository.findByUserOrderByCreateTimeDesc(userService.findCurrentUser(), pageable);
    	
    	List<ProjectSummary> contents = new ArrayList<>();
    	
    	for(final Project project : results.getContent()) {
    		
    		ProjectSummary summay = new ProjectSummary();
    		
    		summay.setId(project.getId());
    		summay.setName(project.getName());
    		summay.setPrincipal(project.getPrincipal().getName());
    		summay.setAddress(project.getAddress());
    		summay.setDailyWages(project.getDailyWages());
    		
    	    final List<LogRecord> records = project.getLogRecords();
            if(!records.isEmpty()) {
                
                summay.setStartTime(records.get(0).getTime());
                summay.setEndTime(records.get(records.size()-1).getTime());
            }
            
            BigDecimal totalHours = new BigDecimal(0);
            BigDecimal totalExtraHours = new BigDecimal(0);
            BigDecimal totalLeaveHours = new BigDecimal(0);
            BigDecimal totalWages = new BigDecimal(0);
            
            for(final LogRecord result : records) {
            
                final BigDecimal dailyWages = result.getProject().getDailyWages();
                Double morningHour = result.getMorningHour();
                Double afternoonHour = result.getAfternoonHour();
                Double eveningHour = result.getEveningHour();
                
                totalHours.add(new BigDecimal(morningHour + afternoonHour));
                totalExtraHours.add(new BigDecimal(eveningHour));
                totalLeaveHours.add(new BigDecimal(8 - (morningHour + afternoonHour)));
               
                //每小时平均工资
                
                BigDecimal ava = dailyWages.divide(new BigDecimal(8));

                totalWages.add(ava.multiply(new BigDecimal(morningHour + afternoonHour), MathContext.DECIMAL32));
            }
			    			
            summay.setWorks(CommonUtil.convertHours(totalHours.doubleValue()));
			summay.setLeaveWorks(CommonUtil.convertHours(totalLeaveHours.doubleValue()));
			summay.setExtraWorks(CommonUtil.convertHours(totalExtraHours.doubleValue()));

			summay.setExpectTotalWages(project.getExpectTotalWages());
			summay.setActualTotalWages(project.getActualTotalWages());
		
    		contents.add(summay);
    	}
    	
    	return new PageImpl<>(contents, pageable, results.getTotalElements());
    }





    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#countByUser(cn.sf.sculpture.user.domain.entity.User)
     */
    @Override
    public Integer countByUser(User user) {
        return repository.countByUser(user);
    }


    

}