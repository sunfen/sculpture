package cn.sf.sculpture.project.service;

import java.math.BigDecimal;
import java.util.List;

import cn.sf.sculpture.project.domain.WagesRecordDTO;
import cn.sf.sculpture.user.domain.entity.User;

public interface WagesRecordService {


    /**
     * @param wagesRecord
     * @return
     * @throws Exception 
     */
    void insert(WagesRecordDTO wagesRecord) throws Exception;

    /**
     * 删除
     * @param logRecordId
     */
	void deleted(Long logRecordId);

	/**
	 * 项目的 工资结账记录
	 * @param projectId
	 * @return
	 */
	List<WagesRecordDTO> findByProjectId(Long projectId);

    /**
     * @param user
     * @return
     */
    BigDecimal countByUser(User user);

}
