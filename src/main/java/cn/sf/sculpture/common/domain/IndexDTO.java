package cn.sf.sculpture.common.domain;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

import cn.sf.sculpture.project.domain.LogRecordDTO;
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
	private BigDecimal workDays;
	
	/**
	 * 日记
	 */
	private Page<LogRecordDTO> pageInfo;

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

    public BigDecimal getWorkDays() {
        return workDays;
    }

    public void setWorkDays(BigDecimal workDays) {
        this.workDays = workDays;
    }

    public Page<LogRecordDTO> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(Page<LogRecordDTO> pageInfo) {
        this.pageInfo = pageInfo;
    }
	

	
}
