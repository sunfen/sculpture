package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.time.Duration;
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
import cn.sf.sculpture.project.domain.entity.WagesRecord;
import cn.sf.sculpture.project.repository.ProjectRepository;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.project.service.WagesRecordService;
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
	private UserService userService;
	@Autowired
    private PrincipalService principalService;
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private LogRecordService logRecordService;
    @Autowired
    private WagesRecordService wagesRecordService;
    
    @Override
    @Transactional
    public void insert(final ProjectDTO project) throws Exception {
        Assert.notNull(project, "project is null");
        
        final User user = userService.findCurrentUser();
        
        final LocalDate startDate = LocalDate.parse(project.getStartTime());
        
        final LocalDateTime startTime = LocalDateTime.of(
        		startDate.getYear(), startDate.getMonthValue(),
        		startDate.getDayOfMonth(), TimeEnum.getHour(project.getStartHour()), 0 ,0);
        
        Project entity = repository.findByUserAndStartTimeAndDeleted(user, startTime, false);
        
        if(entity != null) {
        	
        	if(project.getId() != null && !project.getId().equals(entity.getId())) {
        		throw new DuplicateKeyException("该日期已经有项目了！");
        	}
        }else { 

        	entity = new Project();
        }
        
        Principal principal = project.getPrincipal();
  
        if(principal.getId() == null) {
        	principal = principalService.insert(principal);
        }
 
        
        entity.setDeleted(false);
        entity.setAddress(project.getAddress());
        entity.setCreateTime(CommonUtil.getNow());
        entity.setName(project.getName());
        entity.setPrincipal(principal);
        entity.setStartTime(CommonUtil.formatter(startTime));
        entity.setDailyWages(project.getDailyWages());
        entity.setUser(user);
        repository.save(entity);
               
    }
    
    
    
    @Override
    @Transactional
    public void updateTotalWages(Long projectId) {
    	Project project = repository.getOne(projectId);
    	if(project == null) {
    		return;
    	}
    	
    	BigDecimal total = new BigDecimal(0);
    	
    	List<WagesRecord> wages = wagesRecordService.findByProjectIdInner(projectId);
    	for(final WagesRecord wage : wages) {
    		total.add(wage.getWages());
    	}
    	
    	project.setActualTotalWages(total);
    	
    	repository.save(project);
    	
    }
    
    
    
    @Override
    public ProjectDTO findNew() {
    	Project project = repository.findFirstByUserAndOrderByStartTimeDesc(userService.findCurrentUser());
    	if(project == null) {
    		return null;
    	}
    	
    	LocalDateTime startDate = LocalDateTime.parse(project.getStartTime());
    	
    	ProjectDTO dto = new ProjectDTO();
    	dto.setAddress(project.getAddress());
    	dto.setPrincipal(project.getPrincipal());
    	dto.setDailyWages(project.getDailyWages());
    	dto.setEndTime(project.getEndTime());
    	dto.setId(project.getId());
    	dto.setName(project.getName());
    	dto.setStartHour(TimeEnum.valueOf(String.valueOf(startDate.getMonthValue())).getName());
    	dto.setStartTime(project.getStartTime());
    	
    	return dto;
    }
    
    
    @Override
    @Transactional
    public void deleted(Long projectId) {
    	Project project = repository.getOne(projectId);
    	if(project != null) {
    		project.setDeleted(true);
    		repository.save(project);
    	}
    }
    
    
    
    @Override
    public Page<ProjectSummary> findAll(Pageable pageable){
    	
    	final Page<Project> results = repository.findByUserAndOrderByStartTimeDesc(userService.findCurrentUser(), pageable);
    	
    	List<ProjectSummary> contents = new ArrayList<>();
    	
    	for(final Project project : results.getContent()) {
    		
    		ProjectSummary summay = new ProjectSummary();
    		
    		summay.setAddress(project.getAddress());
    		summay.setEndTime(project.getEndTime());
    		summay.setId(project.getId());
    		summay.setName(project.getName());
    		summay.setPrincipal(project.getPrincipal().getName());
    		summay.setStartTime(project.getStartTime());
    		
    		if(project.getEndTime() != null ) {
    			
    			final LocalDateTime startDate = LocalDateTime.parse(project.getStartTime());
    			final LocalDateTime endDate = LocalDateTime.parse(project.getEndTime());
    			
    			final List<LogRecord> logs = logRecordService.findBetweenInner(project.getId(), startDate, endDate);
    			
    			List<LogRecord> leaveLogs = new ArrayList<>();
    			List<LogRecord> extraLogs = new ArrayList<>();
    			
    			for(final LogRecord log : logs) {
    				if(log.getType().equals(LogEnum.EXTRA_WORK.getHour())) {
    					extraLogs.add(log);
    				}else {
    					leaveLogs.add(log);
    				}
    			}
    			
    			Duration duration = Duration.between(startDate, endDate);
    			
    			Double totalHours = Double.valueOf(duration.toHours());
    			Double leaveWorkHours = 0.0;
    			
    			for(final LogRecord leave : leaveLogs) {
    				leaveWorkHours += leave.getHour();
    			}
    			    			
    			Double extraWorkHours = 0.0;
    			
    			for(final LogRecord extra : extraLogs) {
    				extraWorkHours += extra.getHour();
    			}
    			    			
    			summay.setLeaveWorks(this.convertHours(leaveWorkHours));
    			
    			summay.setExtraWorks(this.convertHours(extraWorkHours));
    			summay.setWorks(this.convertHours(totalHours - leaveWorkHours - extraWorkHours));

    			summay.setExpectTotalWages(project.getExpectTotalWages());
    			summay.setActualTotalWages(project.getActualTotalWages());
    		}
    		
    		contents.add(summay);
    	}
    	
    	return new PageImpl<>(contents, pageable, results.getTotalElements());
    }
    
    
    private String convertHours(Double hours){
    	String day = String.valueOf( Math.floor( hours / 8 ) );
    	String hour = String.valueOf( hours % 8);
    	
    	return Integer.valueOf(day) + "天" + Integer.valueOf(hour)  + "小时";
    }

	@Override
	public Project findOne(Long projectId) {
		
		return repository.getOne(projectId);
	}



}