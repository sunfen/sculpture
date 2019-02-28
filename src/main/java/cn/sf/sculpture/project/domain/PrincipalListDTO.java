package cn.sf.sculpture.project.domain;

import java.util.List;

import cn.sf.sculpture.common.domain.DTO;

public class PrincipalListDTO {
	
	private int id;
	
	
	/**
	 * 字母
	 */
	private String region;
	
	private List<DTO> principals;
	
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

    public List<DTO> getPrincipals() {
        return principals;
    }

    public void setPrincipals(List<DTO> principals) {
        this.principals = principals;
    }

    public PrincipalListDTO(int id, String region, List<DTO> principals) {
        super();
        this.id = id;
        this.region = region;
        this.principals = principals;
    }

}
