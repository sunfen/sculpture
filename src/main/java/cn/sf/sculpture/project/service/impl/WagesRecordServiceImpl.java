package cn.sf.sculpture.project.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.project.domain.WagesRecordDTO;
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
	               
		WagesRecord entity = new WagesRecord();
         
      	entity.setId(wagesRecord.getId());
        entity.setDeleted(false);

        entity.setProject(projectService.findOne(wagesRecord.getProjectId()));
        entity.setRemark(wagesRecord.getRemark());
        entity.setCreateTime(CommonUtil.getNow());
        entity.setWages(wagesRecord.getWages());
        
        
        repository.save(entity);
		
	}

	@Override
	@Transactional
	public void deleted(Long logRecordId) {
		Assert.notNull(logRecordId, "logRecordId is null");
		WagesRecord entity = repository.getOne(logRecordId);
		if(entity != null) {
			entity.setDeleted(true);
			repository.save(entity);
		}
	}


	

	@Override
	public List<WagesRecord> findByProjectIdInner(Long projectId) {
		
		return repository.findByProjectIdAndDeletedOrderByCreateTimeDesc(projectId, false);
	}
	
	@Override
	public List<WagesRecordDTO> findByProjectId(Long projectId) {
		
		final List<WagesRecord> result = repository.findByProjectIdAndDeletedOrderByCreateTimeDesc(projectId, false);
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
			
		}
		return contents;
	}


}
