package cn.sf.sculpture.project.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
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
	private String createTime;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;

	
	/**
	 * 地址
	 */
	@Column(length=128, nullable = false)
	private String address;



    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    
    /**
     * 日工资
     */
    @Column(scale = 2 )
    private BigDecimal dailyWages;
    
    /**
     * 预计总工资
     */
    @Column(scale = 2 )
    private BigDecimal expectTotalWages;
    
    /**
     * 实际总工资
     */
    @Column(scale = 2 )
    private BigDecimal actualTotalWages;
    
    
	public String getAddress() {
		return address;
	}


	public String getCreateTime() {
		return createTime;
	}


	public BigDecimal getDailyWages() {
		return dailyWages;
	}


	public String getEndTime() {
		return endTime;
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


	public String getStartTime() {
		return startTime;
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


	public void setEndTime(String endTime) {
		this.endTime = endTime;
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


	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
	
	
	
}
