package cn.sf.sculpture.project.domain;

public class PrincipalDTO {

	
	private Long id;
	private String name;

	private Integer count;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Integer getCount() {
        return count;
    }
   
	public void setCount(Integer count) {
        this.count = count;
    }
    
    public PrincipalDTO(Long id, String name, Integer count) {
        super();
        this.id = id;
        this.name = name;
        this.count = count;
    }
  
    
    
	public PrincipalDTO() {
		super();
	}
	
	
}
