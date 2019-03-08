package cn.sf.sculpture.project.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
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
import cn.sf.sculpture.project.util.WagesRecordConvert;
import cn.sf.sculpture.user.domain.entity.User;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class WagesRecordServiceImpl implements WagesRecordService {

	@Autowired
	private WagesRecordConvert wagesRecordConvert;
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
        
        entity.setTime(CommonUtil.formatter(startDate));
        entity.setMethod(MethodEnum.getValue(wagesRecord.getMethod()));
        
        repository.save(entity);
        
        
        totalWages = totalWages.add(entity.getWages());
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
		    totalWages = totalWages.subtract(entity.getWages());
	        project.setActualTotalWages(totalWages);
	      
	        projectService.save(project);
	        
			repository.delete(entity);
		}
	}


	

	
	@Override
	public List<WagesRecordDTO> findByProjectId(Long projectId) {
		
		final List<WagesRecord> result = repository.findByProjectIdOrderByCreateTimeDesc(projectId);
		return wagesRecordConvert.convert(result);
	}
	


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.WagesRecordService#countByUser(cn.sf.sculpture.user.domain.entity.User)
     */
    @Override
    public BigDecimal countByUser(User user) {
        return repository.countWagesByProjectUser(user);
    }


}
