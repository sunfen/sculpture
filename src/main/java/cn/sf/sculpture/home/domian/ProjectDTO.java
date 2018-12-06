package cn.sf.sculpture.home.domian;

/**
 * 项目
 * @author a123
 *
 */
public class ProjectDTO {

	private Long id;
	
	/**
	 * 负责人
	 */
	private String pricipal;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 开始时间格式化后
	 */
	private String startTimeFormat;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 地址
	 */
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPricipal() {
		return pricipal;
	}

	public void setPricipal(String pricipal) {
		this.pricipal = pricipal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartTimeFormat() {
		return startTimeFormat;
	}

	public void setStartTimeFormat(String startTimeFormat) {
		this.startTimeFormat = startTimeFormat;
	}
	
	
}
