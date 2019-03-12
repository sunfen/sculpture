package cn.sf.sculpture.project.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.common.domain.entity.StringDayConverter;
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
	 * 上午项目
	 */
	@ManyToOne
	@JoinColumn
	private Project morningProject;
	
	/**
     * 下午项目
     */
    @ManyToOne
    @JoinColumn
    private Project afternoonProject;
    
    /**
     * 晚上项目
     */
    @ManyToOne
    @JoinColumn
    private Project eveningProject;
	
	/**
	 * 上午
	 */
	private Double morningHour;
	/**
	 * 上午
	 */
	private Double afternoonHour;
	/**
	 * 上午
	 */
	private Double eveningHour;
	/**
	 * 共
	 */
	private Double totalHour;
	
	/**
	 * 时间
	 */
	@Convert(converter = StringDayConverter.class)
	private String time;
	
	
	/**
	 * 备注
	 */
	@Column(length=128)
	private String remark;


    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


    public Double getMorningHour() {
        return morningHour;
    }


    public void setMorningHour(Double morningHour) {
        this.morningHour = morningHour;
    }


    public Double getAfternoonHour() {
        return afternoonHour;
    }


    public void setAfternoonHour(Double afternoonHour) {
        this.afternoonHour = afternoonHour;
    }


    public Double getEveningHour() {
        return eveningHour;
    }


    public void setEveningHour(Double eveningHour) {
        this.eveningHour = eveningHour;
    }


    public Double getTotalHour() {
        return totalHour;
    }


    public void setTotalHour(Double totalHour) {
        this.totalHour = totalHour;
    }

    public Project getMorningProject() {
        return morningProject;
    }

    public void setMorningProject(Project morningProject) {
        this.morningProject = morningProject;
    }

    public Project getAfternoonProject() {
        return afternoonProject;
    }

    public void setAfternoonProject(Project afternoonProject) {
        this.afternoonProject = afternoonProject;
    }

    public Project getEveningProject() {
        return eveningProject;
    }

    public void setEveningProject(Project eveningProject) {
        this.eveningProject = eveningProject;
    }
}
