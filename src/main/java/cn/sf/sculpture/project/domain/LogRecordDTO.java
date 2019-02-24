package cn.sf.sculpture.project.domain;

public class LogRecordDTO {
	
	private Long id;
		
	/**
	 * 项目
	 */
	private Long projectId;
	
	/**
	 * 小时
	 */
	private Double hour;
	
	/**
	 * 时间
	 */
	private String time;
	
	/**
	 * 加班/请假
	 */
	private String type;

	
	/**
	 * 备注
	 */
	private String remark;

	
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

	public Double getHour() {
		return hour;
	}

	public void setHour(Double hour) {
		this.hour = hour;
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
	
	
	
	
}
