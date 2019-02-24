package cn.sf.sculpture.project.service;

import java.util.List;

import cn.sf.sculpture.project.domain.WagesRecordDTO;
import cn.sf.sculpture.project.domain.entity.WagesRecord;

public interface WagesRecordService {


    /**
     * @param wagesRecord
     * @return
     * @throws Exception 
     */
    void insert(WagesRecordDTO wagesRecord) throws Exception;


	void deleted(Long logRecordId);

	
	
	List<WagesRecordDTO> findByProjectId(Long projectId);

	List<WagesRecord> findByProjectIdInner(Long projectId);
	
}
