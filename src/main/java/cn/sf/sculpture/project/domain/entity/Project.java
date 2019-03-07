package cn.sf.sculpture.project.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.common.domain.entity.StringDateConverter;
import cn.sf.sculpture.user.domain.entity.User;


/**
 * 项目
 * @author a123
 *
 */
@Entity
@Table(name="project")
public class Project  extends AbstractSecureObject {
	
	/**
	 * 负责人
	 */
	@ManyToOne
	private Principal principal;
	
	/**
	 * 名称
	 */
	@Column(length=128, nullable = false)
	private String name;
	
	/**
	 * 创建时间
	 */
	@Convert(converter = StringDateConverter.class)
	private String createTime;
	
	
	/**
	 * 地址
	 */
	@Column(length=128, nullable = false)
	private String address;



    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    
    /**
     * 共工作多少小时
     */
    private Double totalWorkHour;
    
    /**
     * 日工资
     */
    @Column(scale = 2 )
    private BigDecimal dailyWages = new BigDecimal(0);
    
    /**
     * 预计总工资
     */
    @Column(scale = 2 )
    private BigDecimal expectTotalWages = new BigDecimal(0);
    
    /**
     * 实际总工资
     */
    @Column(scale = 2 )
    private BigDecimal actualTotalWages = new BigDecimal(0);
    
    
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<WagesRecord> wagesRecords;
    
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    @OrderBy("time asc")
    private List<LogRecord> logRecords;
    
    
    
    
	public String getAddress() {
		return address;
	}


	public String getCreateTime() {
		return createTime;
	}


	public BigDecimal getDailyWages() {
		return dailyWages;
	}



	public BigDecimal getExpectTotalWages() {
		return expectTotalWages;
	}


	public String getName() {
		return name;
	}


	public Principal getPrincipal() {
		return principal;
	}


	public User getUser() {
		return user;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public void setDailyWages(BigDecimal dailyWages) {
		this.dailyWages = dailyWages;
	}



	public void setExpectTotalWages(BigDecimal expectTotalWages) {
		this.expectTotalWages = expectTotalWages;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public BigDecimal getActualTotalWages() {
		return actualTotalWages;
	}


	public void setActualTotalWages(BigDecimal actualTotalWages) {
		this.actualTotalWages = actualTotalWages;
	}


    public List<WagesRecord> getWagesRecords() {
        return wagesRecords;
    }


    public void setWagesRecords(List<WagesRecord> wagesRecords) {
        this.wagesRecords = wagesRecords;
    }


    public List<LogRecord> getLogRecords() {
        return logRecords;
    }


    public void setLogRecords(List<LogRecord> logRecords) {
        this.logRecords = logRecords;
    }


    public Double getTotalWorkHour() {
        return totalWorkHour;
    }


    public void setTotalWorkHour(Double totalWorkHour) {
        this.totalWorkHour = totalWorkHour;
    }
	
}
