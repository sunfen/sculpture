package cn.sf.sculpture.project.domain;

import java.util.List;

public class ProjectPrincipalSummary {
	
	private Long id;
	
	/**
	 * 负责人
	 */
	private String principal;
	
	/**
	 * 共个
	 */
	private Integer total;
	
	/**
	 * 项目
	 */
	private List<ProjectSummary> projects;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

    public List<ProjectSummary> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectSummary> projects) {
        this.projects = projects;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
