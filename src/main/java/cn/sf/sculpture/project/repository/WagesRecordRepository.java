package cn.sf.sculpture.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.project.domain.entity.WagesRecord;



/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface WagesRecordRepository extends JpaRepository<WagesRecord, Long>{

	

	
	List<WagesRecord> findByProjectIdAndDeletedOrderByCreateTimeDesc(Long projectId, Boolean deleted);
	

}
