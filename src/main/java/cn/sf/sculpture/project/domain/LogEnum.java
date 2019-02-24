package cn.sf.sculpture.project.domain;

public enum LogEnum {

	LEAVE_WORK("请假", 0),
	EXTRA_WORK("加班", 1);
	
	public static Integer getHour(String name) throws Exception{
		for(final LogEnum time : LogEnum.values()) {
			if(time.getName().equals(name)) {
				return time.getHour();
			}
		}
		throw new Exception();
	}
	
	
	private String name;
	private Integer hour;

	
	


	private LogEnum(String name, Integer hour) {
		this.name = name;
		this.hour = hour;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public Integer getHour() {
		return hour;
	}





	public void setHour(Integer hour) {
		this.hour = hour;
	}


	
	
}
