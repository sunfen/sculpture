package cn.sf.sculpture.project.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.user.domain.entity.User;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface LogRecordRepository extends JpaRepository<LogRecord, Long>{

	

	Page<LogRecord> findByUserAndTimeBetwwenAndDeletedAndOrderByTimeDesc(User findCurrentUser,LocalDateTime startTime,LocalDateTime endTime, Boolean deleted, Pageable pageable);
	
	List<LogRecord> findByUserAndTimeBetwwenAndDeletedAndOrderByTimeDesc(User findCurrentUser,LocalDateTime startTime, LocalDateTime endTime, Boolean deleted);
	
	
	List<LogRecord> findByProjectIdAndUserAndTimeBetwwenAndDeletedAndOrderByTimeDesc(Long projectId, User findCurrentUser,LocalDateTime startTime, LocalDateTime endTime, Boolean deleted);
	
	Page<LogRecord> findByUserAndTimeAfterAndDeletedAndOrderByTimeDesc(User findCurrentUser,LocalDateTime startTime, Boolean deleted, Pageable pageable);

	
}
