package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.domain.LogEnum;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.project.repository.LogRecordRepository;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.ProjectService;
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
    
  

	@Override
    @Transactional
	public void insert(LogRecordDTO logRecord) throws Exception {
		Assert.notNull(logRecord, "logRecord is null");
	        
		
        final LocalDate date = CommonUtil.parserDate(logRecord.getTime());
        date.atTime(8, 0, 0);
      	LogRecord entity = new LogRecord();
         
      	entity.setId(logRecord.getId());
        entity.setDeleted(false);

        entity.setHour(logRecord.getHour());
        entity.setProject(projectService.findOne(logRecord.getProjectId()));
        entity.setRemark(logRecord.getRemark());
        entity.setTime(CommonUtil.formatter(date.atTime(0, 0, 0)));
        entity.setType(LogEnum.getValue(logRecord.getType()));
        
        entity.setUser(userService.findCurrentUser());
        
        repository.save(entity);
		
	}

	@Override
	@Transactional
	public void deleted(Long logRecordId) {
		Assert.notNull(logRecordId, "logRecordId is null");
		LogRecord entity = repository.getOne(logRecordId);
		if(entity != null) {
			entity.setDeleted(true);
			repository.save(entity);
		}
	}

	
	
	@Override
	public Page<LogRecordDTO> findRecentMonth(Pageable pageable) throws Exception {
		final LocalDateTime now = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		
		final Page<LogRecord> results = 
				repository.findByUserAndTimeAfterAndDeletedOrderByTimeAsc(
				    userService.findCurrentUser(), CommonUtil.formatter(now), false, pageable);
			
		return new PageImpl<>(this.convert(results.getContent()), pageable, results.getTotalElements());
	}
	
	

	@Override
	public List<LogRecordDTO> findBetween(Long projectId, LocalDateTime startTime,
			LocalDateTime endTime) throws Exception {
		
	    final List<LogRecord> results =
		    repository.findByProjectIdAndUserAndTimeBetweenAndDeletedOrderByTimeAsc(
		        projectId, userService.findCurrentUser(),  CommonUtil.formatter(startTime), CommonUtil.formatter(endTime), false);
		return this.convert(results);
	}
	
	

	@Override
	public List<LogRecord> findBetweenInner(Long projectId,  LocalDateTime startTime,
			LocalDateTime endTime) {
		
		return repository.findByProjectIdAndUserAndTimeBetweenAndDeletedOrderByTimeAsc(
		    projectId, userService.findCurrentUser(), CommonUtil.formatter(startTime), CommonUtil.formatter(endTime), false);
	}
	


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#getMonthWagesAndWorkDays(cn.sf.sculpture.common.domain.IndexDTO)
     */
    @Override
    public IndexDTO getMonthWagesAndWorkDays(IndexDTO dto) {
        
        final LocalDateTime now = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        
        final List<LogRecord> results = 
                repository.findByUserAndTimeAfterAndDeletedOrderByTimeAsc(
                    userService.findCurrentUser(), CommonUtil.formatter(now), false);
        
        BigDecimal hours = new BigDecimal(0);
        BigDecimal totalWages = new BigDecimal(0);
        
        for(final LogRecord result : results) {
        
            final BigDecimal dailyWages = result.getProject().getDailyWages();
       
            if(result.getType().equals(LogEnum.EXTRA_WORK.getValue())) {
                hours.add(new BigDecimal(result.getHour()));
                totalWages.add(dailyWages);
                
            }else if(result.getType().equals(LogEnum.WORK.getValue())) {
                hours.add(new BigDecimal(result.getHour()));
                totalWages.add(dailyWages.divide(new BigDecimal(result.getHour())));
            }        
        }
        
        dto.setWorkDays(hours.divide(new BigDecimal(8)));
        dto.setMonthWages(totalWages);
        
        return dto;
    }

    
    
    private List<LogRecordDTO> convert(List<LogRecord> source) throws Exception{
        List<LogRecordDTO> contents = new ArrayList<>();
        
        for(final LogRecord log : source) {
            
            LogRecordDTO summary = new LogRecordDTO();
            contents.add(summary);
            
            summary.setHour(log.getHour());
            summary.setProjectId(log.getProject().getId());
            summary.setTime(CommonUtil.parserTime(log.getTime()).toLocalDate().toString());
            summary.setRemark(log.getRemark());
            summary.setType(LogEnum.getName(String.valueOf(log.getType())));
            summary.setOnMonday(CommonUtil.convertWeek(log.getTime()));
            
        }
        return contents;
    }
    
    
 

}
