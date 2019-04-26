package cn.sf.sculpture.project.repository;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.user.domain.entity.User;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface LogRecordRepository extends JpaRepository<LogRecord, Long>{

	Page<LogRecord> findByUserAndTimeBetweenOrderByTimeAsc(User findCurrentUser,String startTime,String endTime,  Pageable pageable);
	
	List<LogRecord> findByUserAndTimeBetweenOrderByTimeAsc(User findCurrentUser,String startTime, String endTime);
	
	Page<LogRecord> findByUserAndTimeAfterOrderByTimeAsc(User findCurrentUser,String startTime, Pageable pageable);

	List<LogRecord> findByUserAndTimeAfterOrderByTimeAsc(User findCurrentUser,String startTime);
	
	List<LogRecord> findByMorningProjectIdOrAfternoonProjectIdOrEveningProjectIdAndTimeBetweenOrderByTimeAsc(Long projectId, Long projectId1, Long projectId2, String startTime, String endTime);

	List<LogRecord> findByMorningProjectIdOrAfternoonProjectIdOrEveningProjectIdOrderByTimeAsc(Long projectId, Long projectId1, Long projectId2);

    /**
     * @param user
     * @param time
     */
	LogRecord findByUserAndTime(User user, String time);

    /**
     * @return
     */
    @Query(value = "select COUNT(l.id) as count, u.openid as openid from user u " + 
        "    left join  log_record l on u.id = l.user_id and date_format(l.time, '%Y') = :year " + 
        "    group by u.id; ", nativeQuery = true)
    List<Map<String, Object>> countByYear(@Param("year") int year);
    
    /**
     * @return
     */
    @Query(value = "select COUNT(l.id) as count from user u " + 
        "    left join  log_record l on u.id = l.user_id and date_format(l.time, '%Y') = :year " + 
        "    where u.id = :userId ; ", nativeQuery = true)
    BigInteger countByYearAndUserId(@Param("year") int year, @Param("userId") Long userId);
	
}
