package cn.sf.sculpture.project.repository;


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

	Page<LogRecord> findByUserAndTimeBetweenAndDeletedOrderByTimeAsc(User findCurrentUser,String startTime,String endTime, Boolean deleted, Pageable pageable);
	
	List<LogRecord> findByUserAndTimeBetweenAndDeletedOrderByTimeAsc(User findCurrentUser,String startTime, String endTime, Boolean deleted);
	
	List<LogRecord> findByProjectIdAndUserAndTimeBetweenAndDeletedOrderByTimeAsc(Long projectId, User findCurrentUser,String startTime, String endTime, Boolean deleted);
	
	Page<LogRecord> findByUserAndTimeAfterAndDeletedOrderByTimeAsc(User findCurrentUser,String startTime, Boolean deleted, Pageable pageable);

	List<LogRecord> findByUserAndTimeAfterAndDeletedOrderByTimeAsc(User findCurrentUser,String startTime, Boolean deleted);

	
}
