package cn.sf.sculpture.project.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.common.domain.entity.StringDateConverter;
import cn.sf.sculpture.user.domain.entity.User;


/**
 * 项目日记
 * @author a123
 *
 */
@Entity
@Table(name="log_record")
public class LogRecord  extends AbstractSecureObject {
	
	
	/**
	 * 项目
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	private Project project;
	
	/**
	 * 小时
	 */
	private Double hour;
	
	/**
	 * 时间
	 */
	@Convert(converter = StringDateConverter.class)
	private String time;
	
	/**
	 * 加班/请假
	 */
	private String type;

	
	/**
	 * 备注
	 */
	@Column(length=128)
	private String remark;


    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
	


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Double getHour() {
		return hour;
	}


	public void setHour(Double hour) {
		this.hour = hour;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
