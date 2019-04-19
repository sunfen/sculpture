package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
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

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.LogRecordRepository;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class LogRecordServiceImpl implements LogRecordService {

	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
    @Autowired
    private LogRecordRepository repository;
    



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#inserts(java.util.List, cn.sf.sculpture.project.domain.entity.Project, cn.sf.sculpture.user.domain.entity.User)
     */
    @Override
    @Transactional
    public List<LogRecord> inserts(final List<LogRecordDTO> logRecords, final Project project, final User user) {
        Assert.notNull(project, "project is null");
        Assert.notNull(user, "user is null");
        Assert.notNull(logRecords, "logRecords is null");
        
        List<LogRecord> entities = new ArrayList<>();
        
        for(final LogRecordDTO record : logRecords) {
            
            final LocalDate date = CommonUtil.parserDate(record.getTime());
            
            LogRecord entity = repository.findByUserAndTime(user, CommonUtil.formatter(date));
            
            if(entity == null) {
                entity =  new LogRecord();
                entity.setUser(user);
                entity.setTime(CommonUtil.formatter(date));
                entity.setMorningProject(project);
                entity.setAfternoonProject(project);
                entity.setEveningProject(project);
                entity.setMorningHour(record.getMorningHour());
                entity.setAfternoonHour(record.getAfternoonHour());
                entity.setEveningHour(record.getEveningHour());
                projectService.addTotalHours(project, entity.getMorningHour() + entity.getAfternoonHour() + entity.getEveningHour());
            }else {
                
                final Project morningProject = entity.getMorningProject();
                final Project afternoonProject = entity.getAfternoonProject();
                final Project eveningProject = entity.getEveningProject();
                if(morningProject == null) {
                    entity.setMorningProject(project);
                    entity.setMorningHour(record.getMorningHour());
                    projectService.addTotalHours(project, entity.getMorningHour());
                }
                if(afternoonProject == null) {
                    entity.setAfternoonProject(project);
                    entity.setAfternoonHour(record.getAfternoonHour());
                    projectService.addTotalHours(project, entity.getAfternoonHour());
                }
                if(eveningProject == null) {
                    entity.setEveningProject(project);
                    entity.setEveningHour(record.getEveningHour());
                    projectService.addTotalHours(project, entity.getEveningHour());
                }
                
            }
            
            entity.setRemark(record.getRemark());
            
            entity.setTotalHour(entity.getMorningHour() + entity.getAfternoonHour() + entity.getEveningHour());
            entities.add(entity);
        }
        
        return repository.saveAll(entities);
    }


    
	@Override
    @Transactional
	public void insert(LogRecordDTO logRecord) throws Exception {
		Assert.notNull(logRecord, "logRecord is null");
		
		final User user = userService.findCurrentUser();
		final LocalDate date = CommonUtil.parserDate(logRecord.getTime());
        
        LogRecord entity = repository.findByUserAndTime(user, CommonUtil.formatter(date));

        if(entity == null) {
            this.create(logRecord, entity, date, user);
        }else {
            this.update(logRecord, entity);
        }
	}
	
	
	private void update(LogRecordDTO logRecord,  LogRecord entity) {
	    final Project oldMorning = entity.getMorningProject();
	    if(oldMorning != null) {
	        projectService.subTotalHours(oldMorning, entity.getMorningHour());
	    }
       
	    final Project oldAfternoon = entity.getAfternoonProject();
	    if(oldAfternoon != null) {
            projectService.subTotalHours(oldAfternoon, entity.getAfternoonHour());
        }
	       
	    final Project oldEvening = entity.getEveningProject();
        if(oldEvening != null) {
            projectService.subTotalHours(oldEvening, entity.getEveningHour());
        }
	    
        if(logRecord.getMorningProjectId() != null) {
            
            final Project morningProject = projectService.findOne(logRecord.getMorningProjectId());
            entity.setMorningProject(morningProject);
            entity.setMorningHour(logRecord.getMorningHour());
            projectService.addTotalHours(morningProject, logRecord.getMorningHour());
        }else {
            entity.setMorningProject(null);
            entity.setMorningHour(0.0);
        }
        if(logRecord.getAfternoonProjectId() != null) {
            
            final Project afternoonProject = projectService.findOne(logRecord.getAfternoonProjectId());
            entity.setAfternoonProject(afternoonProject);
            entity.setAfternoonHour(logRecord.getAfternoonHour());
        
            projectService.addTotalHours(afternoonProject, logRecord.getAfternoonHour());
        }else{
            entity.setAfternoonProject(null);
            entity.setAfternoonHour(0.0); 
        }
        if(logRecord.getEveningProjectId() != null) {
            
            final Project eveningProject = projectService.findOne(logRecord.getEveningProjectId());
            entity.setEveningProject(eveningProject);
            entity.setEveningHour(logRecord.getEveningHour());
            projectService.addTotalHours(eveningProject, logRecord.getEveningHour());
        }else{
            entity.setEveningProject(null);
            entity.setEveningHour(0.0);
        }
        
        entity.setRemark(logRecord.getRemark());
        entity.setTotalHour(entity.getMorningHour() + entity.getAfternoonHour() + entity.getEveningHour());
        
        repository.save(entity);
	}
	
	
   
	private void create(final LogRecordDTO logRecord, LogRecord entity, final LocalDate date, final User user) {
        
	    entity =  new LogRecord();
        
        entity.setUser(user);
        entity.setTime(CommonUtil.formatter(date));
        
        if(logRecord.getMorningProjectId() != null) {
            
            final Project morningProject = projectService.findOne(logRecord.getMorningProjectId());
            entity.setMorningProject(morningProject);
            entity.setMorningHour(logRecord.getMorningHour());
            projectService.addTotalHours(morningProject, logRecord.getMorningHour());
        }else {
            entity.setMorningHour(0.0);
        }
        
        if(logRecord.getAfternoonProjectId() != null) {
            
            final Project afternoonProject = projectService.findOne(logRecord.getAfternoonProjectId());
            entity.setAfternoonProject(afternoonProject);
            entity.setAfternoonHour(logRecord.getAfternoonHour());
            projectService.addTotalHours(afternoonProject, logRecord.getAfternoonHour());
        }else{
            entity.setAfternoonHour(0.0); 
        }
        
        if(logRecord.getEveningProjectId() != null) {
            
            final Project eveningProject = projectService.findOne(logRecord.getEveningProjectId());
            entity.setEveningProject(eveningProject);
            entity.setEveningHour(logRecord.getEveningHour());
            projectService.addTotalHours(eveningProject, logRecord.getEveningHour());
            
        }else{
            entity.setEveningHour(0.0);
        }
        
        entity.setRemark(logRecord.getRemark());
        entity.setTotalHour(entity.getMorningHour() + entity.getAfternoonHour() + entity.getEveningHour());
        
        repository.save(entity);
    }

	
	
	@Override
	public Page<LogRecordDTO> findRecentMonth(Pageable pageable) throws Exception {
	    //0
	    final int number = pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber() ;
	    //7
	    final int size = pageable.getPageSize();
	    Integer startDays = size * (number - 1);
	    startDays = startDays == 0 ? 0 : startDays + number -1;
	    
	    LocalDate endDate = LocalDate.now().minusDays(startDays);
	    LocalDate startDate = endDate.minusDays(size);
		
		final Page<LogRecord> results = 
				repository.findByUserAndTimeBetweenOrderByTimeAsc(
				    userService.findCurrentUser(), CommonUtil.formatter(startDate), CommonUtil.formatter(endDate), pageable);
		
		Map<LocalDate, LogRecordDTO> maps = new HashMap<>();
		
		final List<LogRecordDTO> contens = this.convert(results.getContent());
		
		LocalDate endTime = endDate;
		
		for(int i = 0; i <= size; i ++ ) {
		    final String time = CommonUtil.formatter(endTime);
		    maps.put(endTime, 
		        new LogRecordDTO(time, CommonUtil.convertWeek(time),
		            endTime.getYear(), endTime.getMonthValue(), endTime.getDayOfMonth()));
		    endTime = endTime.minusDays(1);
		}
		
		
		for(final LogRecordDTO content : contens) {
		    final LocalDate date = CommonUtil.parserDate(content.getTime());
		    if(maps.containsKey(date)) {
		        maps.replace(date, content);
		    }
		}
		
		List<LogRecordDTO> newResults = new ArrayList<>();
		for(final LocalDate key : maps.keySet()) {
		    newResults.add(maps.get(key));
		}
		return new PageImpl<>(newResults, pageable, results.getTotalElements());
	}
	
	

	@Override
	public List<LogRecordDTO> findBetween(Long projectId, LocalDate startTime,
	    LocalDate endTime) throws Exception {
		
	    final List<LogRecord> results =
		    repository.findByMorningProjectIdOrAfternoonProjectIdOrEveningProjectIdAndTimeBetweenOrderByTimeAsc(
		        projectId, projectId, projectId, CommonUtil.formatter(startTime), CommonUtil.formatter(endTime));
		return this.convert(results);
	}
	
	

	@Override
	public List<LogRecord> findByProjectId(Long projectId) {
		
		return repository.findByMorningProjectIdOrAfternoonProjectIdOrEveningProjectIdOrderByTimeAsc(projectId, projectId, projectId);
	}
	


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#getMonthWagesAndWorkDays(cn.sf.sculpture.common.domain.IndexDTO)
     */
    @Override
    public IndexDTO getMonthWagesAndWorkDays(IndexDTO dto) {
        
        final LocalDate now = LocalDate.now().withDayOfMonth(1);
        
        final List<LogRecord> results = 
                repository.findByUserAndTimeAfterOrderByTimeAsc(
                    userService.findCurrentUser(), CommonUtil.formatter(now));
        
        BigDecimal totalHours = new BigDecimal(0);
        BigDecimal totalExtraHours = new BigDecimal(0);
        BigDecimal totalLeaveHours = new BigDecimal(0);
        BigDecimal totalWages = new BigDecimal(0);
        
        for(final LogRecord result : results) {
            Double morningHour = result.getMorningHour();
            Double afternoonHour = result.getAfternoonHour();
            Double eveningHour = result.getEveningHour();
           
            totalHours = totalHours.add(new BigDecimal(morningHour + afternoonHour));
            totalExtraHours = totalExtraHours.add(new BigDecimal(eveningHour));
            totalLeaveHours = totalLeaveHours.add(new BigDecimal(8 - (morningHour + afternoonHour)));
            
            if(result.getMorningProject() != null) {
                final BigDecimal morningDailyWages = result.getMorningProject().getDailyWages();
                //每小时平均工资
                BigDecimal avaMorning = morningDailyWages.divide(new BigDecimal(8));
                totalWages = totalWages.add(avaMorning.multiply(new BigDecimal(morningHour ), MathContext.DECIMAL32));
                
            }
            if(result.getAfternoonProject() != null) {
                final BigDecimal afternoonDailyWages = result.getAfternoonProject().getDailyWages();
                BigDecimal avaAfternoon = afternoonDailyWages.divide(new BigDecimal(8));
                totalWages = totalWages.add(avaAfternoon.multiply(new BigDecimal(afternoonHour), MathContext.DECIMAL32));
                
            }
            if(result.getEveningProject() != null) {
                final BigDecimal eveningDailyWages = result.getEveningProject().getDailyWages();
                BigDecimal avaEvening = eveningDailyWages.divide(new BigDecimal(8));
                totalWages = totalWages.add(avaEvening.multiply(new BigDecimal(eveningHour), MathContext.DECIMAL32));
                
            }

        }
        
        dto.setWorkDays(CommonUtil.convertHours(totalHours.doubleValue()));
        dto.setExtraDays(CommonUtil.convertHours(totalExtraHours.doubleValue()));
        dto.setLeaveDays(CommonUtil.convertHours(totalLeaveHours.doubleValue()));
        dto.setMonthWages(totalWages);
        
        return dto;
    }

    
    



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#findBetween(java.lang.String, java.lang.String)
     */
    @Override
    public List<LogRecordDTO> findBetween(Integer year, Integer month) throws Exception {
         final LocalDate startTime = LocalDate.of(year, month, 1);
         final int days = startTime.lengthOfMonth();
         final LocalDate endDate = startTime.plusDays(days);
         
         final List<LogRecord> records = repository.findByUserAndTimeBetweenOrderByTimeAsc(
                 userService.findCurrentUser(), CommonUtil.formatter(startTime), CommonUtil.formatter(endDate));
         
         
         return this.convert(records);
    }



    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#findBetween(java.lang.Long, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public List<LogRecordDTO> findBetween(Long projectId, Integer year, Integer month) {
        final LocalDate startTime = LocalDate.of(year, month, 1);
        final int days = startTime.lengthOfMonth();
        final LocalDate endDate = startTime.plusDays(days);
        
        final List<LogRecord> records = repository.findByMorningProjectIdOrAfternoonProjectIdOrEveningProjectIdAndTimeBetweenOrderByTimeAsc(
            projectId, projectId , projectId,  CommonUtil.formatter(startTime), CommonUtil.formatter(endDate));
        
        return this.convert(records);
    }
    
    
    
    private List<LogRecordDTO> convert(List<LogRecord> source) {
        List<LogRecordDTO> contents = new ArrayList<>();
        
        for(final LogRecord log : source) {
            final LocalDate date = CommonUtil.parserDate(log.getTime());
            
            LogRecordDTO summary = new LogRecordDTO();
            contents.add(summary);
            summary.setId(log.getId());
            
            if(log.getMorningProject() != null) {
                
                summary.setMorningProjectId(log.getMorningProject().getId());
            }
            
            if(log.getAfternoonProject() != null) {
                
                summary.setAfternoonProjectId(log.getAfternoonProject().getId());
            }

            if(log.getEveningProject() != null) {
                
                summary.setEveningProjectId(log.getEveningProject().getId());
            }
            
            summary.setTime(date.toString());
            summary.setRemark(log.getRemark());
            summary.setOnMonday(CommonUtil.convertWeek(log.getTime()));
            
            summary.setAfternoonHour(log.getAfternoonHour());
            summary.setEveningHour(log.getEveningHour());
            summary.setMorningHour(log.getMorningHour());
            summary.setTotalHour(log.getTotalHour()); 
            
            summary.setDay(date.getDayOfMonth());
            summary.setMonth(date.getMonthValue());
            summary.setYear(date.getYear());
        }
        return contents;
    }



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#count(int)
     */
    @Override
    public List<Map<String, Object>> count(int year) {
       
         
        return repository.countByYear(year);
    }





 

}
