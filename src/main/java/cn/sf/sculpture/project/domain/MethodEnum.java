package cn.sf.sculpture.project.domain;

public enum MethodEnum {

	CARD("银行卡", 1),
	WEIXIN("微信", 3),
    ALIPAY("支付宝", 5);
	
	public static Integer getValue(String name) {
		for(final MethodEnum time : MethodEnum.values()) {
			if(time.getName().equals(name)) {
				return time.getValue();
			}
		}
	     throw new IllegalArgumentException();
	}
	
   public static String getName(Integer value) {
        for(final MethodEnum time : MethodEnum.values()) {
            if(time.getValue().equals(value)) {
                return time.getName();
            }
        }
        
        throw new IllegalArgumentException();
    }
	
	private String name;
	private Integer value;


	private MethodEnum(String name, Integer value) {
		this.name = name;
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
