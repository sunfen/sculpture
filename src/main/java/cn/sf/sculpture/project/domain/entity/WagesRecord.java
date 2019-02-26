package cn.sf.sculpture.project.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.common.domain.entity.StringDateConverter;


/**
 * 项目日记
 * @author a123
 *
 */
@Entity
@Table(name="wages_record")
public class WagesRecord  extends AbstractSecureObject {
	
	
	/**
	 * 项目
	 */
	@ManyToOne
	private Project project;
	
	/**
	 * 收入
	 */
	private BigDecimal wages  = new BigDecimal(0);

	
	/**
	 * 时间
	 */
    @Convert(converter = StringDateConverter.class)
	private String createTime;
	
	/**
	 * 备注
	 */
	@Column(length=128, nullable = false)
	private String remark;

	


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


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


	
	
}
