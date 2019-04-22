package cn.sf.sculpture.project.domain;

import java.math.BigDecimal;
import java.util.List;

import cn.sf.sculpture.document.domain.DocumentVO;

public class ProjectSummary {
	
	private Long id;
	
	/**
	 * 负责人
	 */
	private String principal;
	
	/**
	 * 备注
	 */
	private String name;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 日工资
	 */
	private BigDecimal dailyWages;
	
	/**
	 * 预计总工资
	 */
	private BigDecimal expectTotalWages  = new BigDecimal(0);
	
	/**
	 * 实际总工资
	 */
	private BigDecimal actualTotalWages  = new BigDecimal(0);
	
	/**
	 * 工作 几小时 几天
	 */
	private String  works;
	
	/**
	 * 请假几小时  几天
	 */
	private String  leaveWorks;
	
	/**
	 * 加班几小时 几天
	 */
	private String  extraWorks;
	
	/**
	 * 地址
	 */
	private String address;

    
    private List<DocumentVO> images;
    
    
    
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

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

	public String getWorks() {
		return works;
	}

	public void setWorks(String works) {
		this.works = works;
	}

	public String getLeaveWorks() {
		return leaveWorks;
	}

	public void setLeaveWorks(String leaveWorks) {
		this.leaveWorks = leaveWorks;
	}

	public String getExtraWorks() {
		return extraWorks;
	}

	public void setExtraWorks(String extraWorks) {
		this.extraWorks = extraWorks;
	}

    public BigDecimal getDailyWages() {
        return dailyWages;
    }

    public void setDailyWages(BigDecimal dailyWages) {
        this.dailyWages = dailyWages;
    }

    public List<DocumentVO> getImages() {
        return images;
    }

    public void setImages(List<DocumentVO> images) {
        this.images = images;
    }
	
    
}
