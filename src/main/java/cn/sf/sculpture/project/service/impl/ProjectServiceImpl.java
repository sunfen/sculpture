package cn.sf.sculpture.project.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.project.domain.LogEnum;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.TimeEnum;
import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.ProjectRepository;
import cn.sf.sculpture.project.service.LogRecordService;
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
    @Autowired
    private LogRecordService logRecordService;
    
    

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
        
        if(project.getStartTime().length() > 11) {
            
            project.setStartTime(project.getStartTime().substring(0, 10));
        }
        
        final LocalDate startDate = LocalDate.parse(project.getStartTime());
        
        final LocalDateTime startTime = LocalDateTime.of(
        		startDate.getYear(), startDate.getMonthValue(),
        		startDate.getDayOfMonth(), TimeEnum.getHour(project.getStartHour()), 0 ,0);

        
        Project entity = repository.findByUserAndStartTime(user, CommonUtil.formatter(startTime));
        
        if(entity != null) {
        	
        	if(project.getId() != null && !project.getId().equals(entity.getId())) {
        		throw new DuplicateKeyException("该日期已经有项目了！");
        	}
        }else { 

        	entity = new Project();
        	entity.setUser(user);
        }
        
        Principal principal = project.getPrincipal();
  
        if(principal.getId() == null) {
        	principal = principalService.insert(principal);
        }
 
        entity.setAddress(project.getAddress());
        entity.setCreateTime(CommonUtil.getNow());
        entity.setName(project.getName());
        entity.setPrincipal(principal);
        entity.setStartTime(CommonUtil.formatter(startTime));
        entity.setDailyWages(project.getDailyWages());
        
        
        if(project.getEndTime() != null && project.getEndTime() != "") {
            if(project.getEndTime().length() > 11) {
                
                project.setEndTime(project.getEndTime().substring(0, 10));
            }
            
            final LocalDate endDate = LocalDate.parse(project.getEndTime());
            
            final LocalDateTime endTime = LocalDateTime.of(
                endDate.getYear(), endDate.getMonthValue(),
                endDate.getDayOfMonth(), TimeEnum.getHour(project.getEndHour()), 0 ,0);
          
            entity.setStartTime(CommonUtil.formatter(endTime));
        }
        
        repository.save(entity);
               
    }
    
    
    
    
    @Override
    public ProjectDTO findNew() throws Exception {
    	Project project = repository.findFirstByUserOrderByStartTimeDesc(userService.findCurrentUser());
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

        final List<Project> results = repository.findByUserOrderByStartTimeDesc(userService.findCurrentUser());
        
        List<ProjectSummary> contents = new ArrayList<>();
        
        for(final Project project : results) {
            
            ProjectSummary summay = new ProjectSummary();
            
            summay.setAddress(project.getAddress());
            summay.setId(project.getId());
            summay.setName(project.getName());
            summay.setPrincipal(project.getPrincipal().getName());
            summay.setStartTime(project.getStartTime());
            summay.setDailyWages(project.getDailyWages());
            
            contents.add(summay);
        }
        
        return contents;
    }
    
    
    @Override
    public Page<ProjectSummary> findAll(Pageable pageable){
    	
    	final Page<Project> results = 
    	    repository.findByUserOrderByStartTimeDesc(userService.findCurrentUser(), pageable);
    	
    	List<ProjectSummary> contents = new ArrayList<>();
    	
    	for(final Project project : results.getContent()) {
    		
    		ProjectSummary summay = new ProjectSummary();
    		
    		summay.setAddress(project.getAddress());
    		summay.setEndTime(project.getEndTime());
    		summay.setId(project.getId());
    		summay.setName(project.getName());
    		summay.setPrincipal(project.getPrincipal().getName());
    		summay.setStartTime(project.getStartTime());
    		summay.setDailyWages(project.getDailyWages());
    	
			final List<LogRecord> logs = logRecordService.findByProjectId(project.getId());
			
			List<LogRecord> leaveLogs = new ArrayList<>();
			List<LogRecord> extraLogs = new ArrayList<>();
			List<LogRecord> waorkLogs = new ArrayList<>();
			
			for(final LogRecord log : logs) {
				if(log.getType().equals(LogEnum.EXTRA_WORK.getValue())) {
					extraLogs.add(log);
				}else if(log.getType().equals(LogEnum.WORK.getValue())){
				    waorkLogs.add(log);
				}else {
                    leaveLogs.add(log);
                }
			}
			
			Double workHours = 0.0;
			Double leaveWorkHours = 0.0;
			Double extraWorkHours = 0.0;
			
			for(final LogRecord extra : waorkLogs) {
			    workHours += extra.getHour();
			}
			
			for(final LogRecord leave : leaveLogs) {
			    leaveWorkHours += leave.getHour();
			    workHours += 8 - leave.getHour();
			}
            
            for(final LogRecord extra : extraLogs) {
                extraWorkHours += extra.getHour();
            }

			    			
			summay.setLeaveWorks(CommonUtil.convertHours(leaveWorkHours));
			
			summay.setExtraWorks(CommonUtil.convertHours(extraWorkHours));
			summay.setWorks(CommonUtil.convertHours(workHours));

			summay.setExpectTotalWages(project.getExpectTotalWages());
			summay.setActualTotalWages(project.getActualTotalWages());
		
    		contents.add(summay);
    	}
    	
    	return new PageImpl<>(contents, pageable, results.getTotalElements());
    }


    
    

}