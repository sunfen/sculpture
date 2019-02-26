package cn.sf.sculpture.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.domain.entity.LogRecord;

public interface LogRecordService {


    /**
     * @param username
     * @return
     * @throws Exception 
     */
    void insert(LogRecordDTO logRecord) throws Exception;


	void deleted(Long logRecordId);

	
	Page<LogRecordDTO> findRecentMonth(Pageable pageable) throws Exception;
	
	List<LogRecord> findBetweenInner(Long projectId, LocalDateTime startTime, LocalDateTime endTime);
	
	List<LogRecordDTO> findBetween(Long projectId, LocalDateTime startTime, LocalDateTime endTime) throws Exception;


    /**
     * @param dto
     * @return
     */
    IndexDTO getMonthWagesAndWorkDays(IndexDTO dto);

 
	
}
