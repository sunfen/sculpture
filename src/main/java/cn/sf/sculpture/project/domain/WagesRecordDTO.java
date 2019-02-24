package cn.sf.sculpture.project.domain;

import java.math.BigDecimal;

import javax.persistence.Column;


/**
 * 项目工资日记
 * @author a123
 *
 */
public class WagesRecordDTO {
	
	
	
	/**
	 * 项目
	 */
	private Long id;
	
	/**
	 * 项目
	 */
	private Long projectId;
	
	/**
	 * 收入
	 */
	private BigDecimal wages;

	
	/**
	 * 时间
	 */
	private String createTime;
	
	/**
	 * 备注
	 */
	@Column(length=128, nullable = false)
	private String remark;

	
	public BigDecimal getWages() {
		return wages;
	}


	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}
