package cn.sf.sculpture.project.domain;

public enum TimeEnum {

	MORNING("上午", 8),
	AFTERNOON("下午", 2);
	
	public static Integer getHour(String name) throws Exception{
		for(final TimeEnum time : TimeEnum.values()) {
			if(time.getName().equals(name)) {
				return time.getHour();
			}
		}
		throw new Exception();
	}
	
   public static String getName(Integer hour) throws Exception{
        for(final TimeEnum time : TimeEnum.values()) {
            if(time.getHour().equals(hour)) {
                return time.getName();
            }
        }
        throw new Exception();
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
