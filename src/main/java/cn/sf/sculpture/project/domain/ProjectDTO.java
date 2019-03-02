package cn.sf.sculpture.project.domain;

import java.math.BigDecimal;

import cn.sf.sculpture.project.domain.entity.Principal;

public class ProjectDTO {
	
	private Long id;
	
	/**
	 * 负责人
	 */
	private Principal principal;
	
	/**
	 * 备注
	 */
	private String name;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 开始时间格式小时
	 */
	private String startHour;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
     * 结束时间格式小时
     */
    private String endHour;
	
	
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 日工资
	 */
	private BigDecimal dailyWages = new BigDecimal(0);
	
    /**
     * 预计总工资
     */
    private BigDecimal expectTotalWages = new BigDecimal(0);
    
    /**
     * 实际总工资
     */
    private BigDecimal actualTotalWages = new BigDecimal(0);
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public BigDecimal getDailyWages() {
		return dailyWages;
	}

	public void setDailyWages(BigDecimal dailyWages) {
		this.dailyWages = dailyWages;
	}

    public BigDecimal getExpectTotalWages() {
        return expectTotalWages;
    }

    public void setExpectTotalWages(BigDecimal expectTotalWages) {
        this.expectTotalWages = expectTotalWages;
    }

    public BigDecimal getActualTotalWages() {
        return actualTotalWages;
    }

    public void setActualTotalWages(BigDecimal actualTotalWages) {
        this.actualTotalWages = actualTotalWages;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

	
}
