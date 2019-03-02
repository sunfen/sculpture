package cn.sf.sculpture.project.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.project.domain.entity.WagesRecord;
import cn.sf.sculpture.user.domain.entity.User;



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
    BigDecimal countWagesByProjectUser(User user);
	

}
