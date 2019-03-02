package cn.sf.sculpture.project.domain;

import java.util.List;


public class PrincipalListDTO {
	
	private int id;
	
	
	/**
	 * 字母
	 */
	private String region;
	
	private List<PrincipalDTO> principals;
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<PrincipalDTO> getPrincipals() {
        return principals;
    }

    public void setPrincipals(List<PrincipalDTO> principals) {
        this.principals = principals;
    }

    public PrincipalListDTO(int id, String region, List<PrincipalDTO> principals) {
        super();
        this.id = id;
        this.region = region;
        this.principals = principals;
    }

}
