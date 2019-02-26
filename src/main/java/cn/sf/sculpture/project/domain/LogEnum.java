package cn.sf.sculpture.project.domain;

public enum LogEnum {

	LEAVE_WORK("请假", "leave"),
	EXTRA_WORK("加班", "extra"),
    WORK("上班", "work");
	
	public static String getValue(String name) throws Exception{
		for(final LogEnum time : LogEnum.values()) {
			if(time.getValue().equals(name)) {
				return time.getValue();
			}
		}
		throw new Exception();
	}
	
   public static String getName(String value) throws Exception{
        for(final LogEnum time : LogEnum.values()) {
            if(time.getValue().equals(value)) {
                return time.getName();
            }
        }
        throw new Exception();
    }

	
	private String name;
	private String value;

	
	private LogEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	
	
}
