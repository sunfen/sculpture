package cn.sf.sculpture.project.domain;

import java.math.BigDecimal;


public class CountDTO {
	
	/**
	 * 负责人个数
	 */
	private Integer countPrincipal = 0;
	
	
	/**
     * 项目个数
     */
    private Integer countProject = 0;
    
    
    
    /**
     * 总工资
     */
    private BigDecimal countWages = new BigDecimal(0);

    
    
    
    public Integer getCountPrincipal() {
        return countPrincipal;
    }

    public void setCountPrincipal(Integer countPrincipal) {
        this.countPrincipal = countPrincipal;
    }

    public Integer getCountProject() {
        return countProject;
    }

    public void setCountProject(Integer countProject) {
        this.countProject = countProject;
    }

    public BigDecimal getCountWages() {
        return countWages;
    }

    public void setCountWages(BigDecimal countWages) {
        this.countWages = countWages;
    }

    
    
    public CountDTO(Integer countPrincipal, Integer countProject, BigDecimal countWages) {
        super();
        this.countPrincipal = countPrincipal;
        this.countProject = countProject;
        this.countWages = countWages;
    }

    /**
     * 
     */
    public CountDTO() {
        super();
    }
	
}
