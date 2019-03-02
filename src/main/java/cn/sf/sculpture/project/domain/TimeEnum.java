package cn.sf.sculpture.project.domain;

public enum TimeEnum {

	MORNING("上午", 8),
	AFTERNOON("下午", 2),
    NOON("中午", 12),
    EVENING("晚上", 18);
	
	public static Integer getHour(String name) {
		for(final TimeEnum time : TimeEnum.values()) {
			if(time.getName().equals(name)) {
				return time.getHour();
			}
		}
	     throw new IllegalArgumentException();
	}
	
   public static String getName(Integer hour) {
        for(final TimeEnum time : TimeEnum.values()) {
            if(time.getHour().equals(hour)) {
                return time.getName();
            }
        }
        throw new IllegalArgumentException();
    }
	
	private String name;
	private Integer hour;


	private TimeEnum(String name, Integer hour) {
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
