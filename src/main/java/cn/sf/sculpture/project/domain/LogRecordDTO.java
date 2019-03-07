package cn.sf.sculpture.project.domain;

public class LogRecordDTO {
	
	private Long id;
		
	/**
	 * 项目
	 */
	private Long projectId;
	
	/**
	 * 时间
	 */
	private String time;
	
	/**
	 * 周几
	 */
	private String onMonday;
    
    /**
     * 上午
     */
    private Double morningHour;
    /**
     * 下午
     */
    private Double afternoonHour;
    /**
     * 晚上
     */
    private Double eveningHour;
    /**
     * 共
     */
    private Double totalHour;
	
	/**
	 * 备注
	 */
	private String remark;
	
	private Integer year;
	
	private Integer month;
	
	private Integer day;
	
    
    public LogRecordDTO(String time, String onMonday, Integer year, Integer month, Integer day) {
        super();
        this.time = time;
        this.onMonday = onMonday;
        this.year = year;
        this.month = month;
        this.day = day;
    }



    /**
     * 
     */
    public LogRecordDTO() {
         super();
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

    public String getOnMonday() {
        return onMonday;
    }

    public void setOnMonday(String onMonday) {
        this.onMonday = onMonday;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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
    
    
	
}
