package cn.sf.sculpture.project.service;

import java.time.LocalDate;
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
	
	List<LogRecord> findByProjectId(Long projectId);
	
	List<LogRecordDTO> findBetween(Long projectId, LocalDate startTime, LocalDate endTime) throws Exception;


    /**
     * @param dto
     * @return
     */
    IndexDTO getMonthWagesAndWorkDays(IndexDTO dto);


    /**
     * 获取某年某月的日志
     * @param year
     * @param month
     * @return
     * @throws Exception 
     */
    List<LogRecordDTO> findBetween(Integer year, Integer month) throws Exception;

 
	
}
