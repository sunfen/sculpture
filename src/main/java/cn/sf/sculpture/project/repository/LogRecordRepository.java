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

	Page<LogRecord> findByUserAndTimeBetweenOrderByTimeAsc(User findCurrentUser,String startTime,String endTime,  Pageable pageable);
	
	List<LogRecord> findByUserAndTimeBetweenOrderByTimeAsc(User findCurrentUser,String startTime, String endTime);
	
	List<LogRecord> findByProjectIdAndTimeBetweenOrderByTimeAsc(Long projectId, String startTime, String endTime);

	List<LogRecord> findByProjectIdOrderByTimeAsc(Long projectId);
	
	Page<LogRecord> findByUserAndTimeAfterOrderByTimeAsc(User findCurrentUser,String startTime, Pageable pageable);

	List<LogRecord> findByUserAndTimeAfterOrderByTimeAsc(User findCurrentUser,String startTime);

    /**
     * @param user
     * @param time
     */
	LogRecord findByUserAndTimeAndProjectId(User user, String time, Long projectId);

	
}
