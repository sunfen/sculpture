package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.project.domain.MethodEnum;
import cn.sf.sculpture.project.domain.WagesRecordDTO;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.domain.entity.WagesRecord;
import cn.sf.sculpture.project.repository.WagesRecordRepository;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.project.service.WagesRecordService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class WagesRecordServiceImpl implements WagesRecordService {

	
	@Autowired
	private ProjectService projectService;
    
	@Autowired
    private WagesRecordRepository repository;
    
  

	@Override
    @Transactional
	public void insert(WagesRecordDTO wagesRecord) throws Exception {
		Assert.notNull(wagesRecord, "wagesRecord is null");
		
		final Project project = projectService.findOne(wagesRecord.getProjectId());
		BigDecimal totalWages = project.getActualTotalWages();
		WagesRecord entity;
		
		if(wagesRecord.getId() != null) {
	        entity = repository.getOne(wagesRecord.getId());
	        totalWages = totalWages.subtract(entity.getWages());
	    }else {
	        
	        entity = new WagesRecord();
	        entity.setProject(project);
	        entity.setCreateTime(CommonUtil.getNow());
	    }           

        entity.setRemark(wagesRecord.getRemark());
        entity.setWages(wagesRecord.getWages());
        if(wagesRecord.getTime().length() > 11) {
            
            wagesRecord.setTime(wagesRecord.getTime().substring(0, 10));
        }
        
        final LocalDate startDate = LocalDate.parse(wagesRecord.getTime());
        
        final LocalDateTime startTime = LocalDateTime.of(
                startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), 0, 0 ,0);
        
        entity.setTime(CommonUtil.formatter(startTime));
        entity.setMethod(MethodEnum.getValue(wagesRecord.getMethod()));
        
        repository.save(entity);
        
        
        totalWages.add(entity.getWages());
        project.setActualTotalWages(totalWages);
        projectService.save(project);
		
	}

	
	
	@Override
	@Transactional
	public void deleted(Long logRecordId) {
		Assert.notNull(logRecordId, "logRecordId is null");
		WagesRecord entity = repository.getOne(logRecordId);
	
		if(entity != null) {
		    
		    final Project project = projectService.findOne(entity.getProject().getId());
	        
		    BigDecimal totalWages = project.getActualTotalWages();
	        totalWages.subtract(entity.getWages());
	        project.setActualTotalWages(totalWages);
	      
	        projectService.save(project);
	        
			repository.delete(entity);
		}
	}


	

	
	@Override
	public List<WagesRecordDTO> findByProjectId(Long projectId) {
		
		final List<WagesRecord> result = repository.findByProjectIdOrderByCreateTimeDesc(projectId);
		return this.convert(result);
	}
	
	
	private List<WagesRecordDTO> convert(List<WagesRecord> source){
		List<WagesRecordDTO> contents = new ArrayList<>();
		
		for(final WagesRecord log : source) {
			
			WagesRecordDTO summary = new WagesRecordDTO();
			contents.add(summary);
			
			summary.setProjectId(log.getProject().getId());
			summary.setCreateTime(log.getCreateTime());
			summary.setRemark(log.getRemark());
			summary.setWages(log.getWages());
			summary.setId(log.getId());
			summary.setTime(log.getTime());
			summary.setMethod(MethodEnum.getName(log.getMethod()));
		}
		return contents;
	}


}
