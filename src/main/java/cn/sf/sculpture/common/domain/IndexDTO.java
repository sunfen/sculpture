package cn.sf.sculpture.common.domain;

import java.math.BigDecimal;

import cn.sf.sculpture.project.domain.ProjectDTO;

public class IndexDTO {
	
    /**
     * 项目
     */
	private ProjectDTO project;
	
	/**
	 * 月工资
	 */
	private BigDecimal monthWages;
	
	
	/**
	 * 出勤天数
	 */
	private String workDays;
	
	   
    /**
     * 加班天数
     */
    private String extraDays;
    
    /**
     * 缺勤天数
     */
    private String leaveDays;
	
    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    public BigDecimal getMonthWages() {
        return monthWages;
    }

    public void setMonthWages(BigDecimal monthWages) {
        this.monthWages = monthWages;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }


    public String getExtraDays() {
        return extraDays;
    }

    public void setExtraDays(String extraDays) {
        this.extraDays = extraDays;
    }

    public String getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(String leaveDays) {
        this.leaveDays = leaveDays;
    }
	
}
