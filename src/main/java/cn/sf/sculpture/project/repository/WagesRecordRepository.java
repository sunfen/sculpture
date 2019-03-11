package cn.sf.sculpture.project.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.sf.sculpture.project.domain.entity.WagesRecord;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface WagesRecordRepository extends JpaRepository<WagesRecord, Long>{

	
	List<WagesRecord> findByProjectIdOrderByCreateTimeDesc(Long projectId);

    /**
     * @param user
     * @return
     */
	@Query(value = "select SUM(t.wages) from wages_record t "
    	   + "    left join project p on p.id = t.project_id "
    	   + "    where p.user_id = :userId  ", nativeQuery = true)
    BigDecimal countWagesByProjectUser(@Param("userId") Long userId);
	

}
