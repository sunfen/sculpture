package cn.sf.sculpture.project.domain;

import java.math.BigDecimal;
import java.util.List;

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
	 * 结束时间
	 */
	private String endTime;
	
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
	
    private String method;
    
    private List<LogRecordDTO> logRecords;
    
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

    
    public List<LogRecordDTO> getLogRecords() {
        return logRecords;
    }

    public void setLogRecords(List<LogRecordDTO> logRecords) {
        this.logRecords = logRecords;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
