package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import cn.sf.sculpture.project.domain.LogEnum;
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
    
  

	@Override
    @Transactional
	public void insert(LogRecordDTO logRecord) throws Exception {
		Assert.notNull(logRecord, "logRecord is null");
		
		final Project project = projectService.findOne(logRecord.getProjectId());
		final User user = userService.findCurrentUser();

		final LocalDate date = CommonUtil.parserDate(logRecord.getTime());
        
        LogRecord entity = repository.findByUserAndTime(user, CommonUtil.formatter(date.atTime(0, 0, 0)));

        if(entity == null) {
            entity =  new LogRecord();
            entity.setUser(user);
        }
        
        entity.setHour(logRecord.getHour());
        entity.setProject(project);
        entity.setRemark(logRecord.getRemark());
        entity.setTime(CommonUtil.formatter(date.atTime(8, 0, 0)));
        entity.setType(LogEnum.getValue(logRecord.getType()));
        
        repository.save(entity);
	}
	
	

	@Override
	@Transactional
	public void deleted(Long logRecordId) {
		Assert.notNull(logRecordId, "logRecordId is null");
		LogRecord entity = repository.getOne(logRecordId);
		if(entity != null) {
			repository.delete(entity);
		}
	}

	
	
	@Override
	public Page<LogRecordDTO> findRecentMonth(Pageable pageable) throws Exception {
	    //0
	    final int number = pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber() ;
	    //7
	    final int size = pageable.getPageSize();
	    Integer startDays = size * (number - 1);
	    startDays = startDays == 0 ? 0 : startDays + number -1;
	    
	    LocalDateTime endDate = LocalDateTime.now().minusDays(startDays).withHour(23).withMinute(59).withSecond(59);
	    LocalDateTime startDate = endDate.minusDays(size).withHour(0).withMinute(0).withSecond(0);
		
		final Page<LogRecord> results = 
				repository.findByUserAndTimeBetweenOrderByTimeAsc(
				    userService.findCurrentUser(), CommonUtil.formatter(startDate), CommonUtil.formatter(endDate), pageable);
		
		Map<LocalDate, LogRecordDTO> maps = new HashMap<>();
		
		final List<LogRecordDTO> contens = this.convert(results.getContent());
		
		LocalDate endTime = endDate.toLocalDate();
		
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
	public List<LogRecordDTO> findBetween(Long projectId, LocalDateTime startTime,
			LocalDateTime endTime) throws Exception {
		
	    final List<LogRecord> results =
		    repository.findByProjectIdAndTimeBetweenOrderByTimeAsc(
		        projectId,  CommonUtil.formatter(startTime), CommonUtil.formatter(endTime));
		return this.convert(results);
	}
	
	

	@Override
	public List<LogRecord> findByProjectId(Long projectId) {
		
		return repository.findByProjectIdOrderByTimeAsc(projectId);
	}
	


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#getMonthWagesAndWorkDays(cn.sf.sculpture.common.domain.IndexDTO)
     */
    @Override
    public IndexDTO getMonthWagesAndWorkDays(IndexDTO dto) {
        
        final LocalDateTime now = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        
        final List<LogRecord> results = 
                repository.findByUserAndTimeAfterOrderByTimeAsc(
                    userService.findCurrentUser(), CommonUtil.formatter(now));
        
        
        
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

    
    



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.LogRecordService#findBetween(java.lang.String, java.lang.String)
     */
    @Override
    public List<LogRecordDTO> findBetween(Integer year, Integer month) throws Exception {
         final LocalDateTime startTime = LocalDateTime.of(year, month, 1, 0, 0, 0);
         final int days = startTime.toLocalDate().lengthOfMonth();
         final LocalDateTime endDate = startTime.plusDays(days).withHour(0).withSecond(0).withMinute(0);
         
         final List<LogRecord> records = repository.findByUserAndTimeBetweenOrderByTimeAsc(
                 userService.findCurrentUser(), CommonUtil.formatter(startTime), CommonUtil.formatter(endDate));
         
         
         return this.convert(records);
    }
    
    
    
    
    private List<LogRecordDTO> convert(List<LogRecord> source) throws Exception{
        List<LogRecordDTO> contents = new ArrayList<>();
        
        for(final LogRecord log : source) {
            LocalDate date = CommonUtil.parserTime(log.getTime()).toLocalDate();
            LogRecordDTO summary = new LogRecordDTO();
            contents.add(summary);
            
            summary.setHour(log.getHour());
            summary.setProjectId(log.getProject().getId());
            summary.setTime(date.toString());
            summary.setRemark(log.getRemark());
            summary.setType(LogEnum.getName(String.valueOf(log.getType())));
            summary.setOnMonday(CommonUtil.convertWeek(log.getTime()));
            
            summary.setDay(date.getDayOfMonth());
            summary.setMonth(date.getMonthValue());
            summary.setYear(date.getYear());
        }
        return contents;
    }

 

}
