 package cn.sf.sculpture.project.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.LogRecord;
import cn.sf.sculpture.project.domain.entity.Project;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/02/27
 */
@Component
public class ProjectConvert {

    
    public ProjectDTO convertDTO (Project project) throws Exception {
        if(project == null ) {
            return null;
        }
        
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setAddress(project.getAddress());
        dto.setPrincipal(project.getPrincipal());
        dto.setDailyWages(project.getDailyWages());

        dto.setActualTotalWages(project.getActualTotalWages());
        dto.setExpectTotalWages(project.getExpectTotalWages());
        
        final List<LogRecord> morningLogRecords = project.getMorningLogRecords();
        final List<LogRecord> afternoonLogRecords = project.getAfternoonLogRecords();
        final List<LogRecord> eveningLogRecords = project.getEveningLogRecords();
        
        if(!morningLogRecords.isEmpty()) {
            dto.setStartTime(morningLogRecords.get(0).getTime());
            dto.setEndTime(morningLogRecords.get(morningLogRecords.size()-1).getTime());
        }
        
        
        if(!afternoonLogRecords.isEmpty()) {
            if(dto.getStartTime() != null) {
                final String time = afternoonLogRecords.get(0).getTime();
                final String endTime = afternoonLogRecords.get(afternoonLogRecords.size()-1).getTime();
                final LocalDate startDate = CommonUtil.parserDate(time);
                final LocalDate endDate = CommonUtil.parserDate(endTime);

                if(startDate.isBefore(CommonUtil.parserDate(dto.getStartTime()))) {
                    dto.setStartTime(time);
                }
                
                if(CommonUtil.parserDate(dto.getEndTime()).isBefore(endDate)) {
                    dto.setEndTime(endTime);
                }
            }
            
        }
        
        
        if(!eveningLogRecords.isEmpty()) {
            if(dto.getStartTime() != null) {
                final String time = eveningLogRecords.get(0).getTime();
                final String endTime = eveningLogRecords.get(eveningLogRecords.size()-1).getTime();
                final LocalDate startDate = CommonUtil.parserDate(time);
                final LocalDate endDate = CommonUtil.parserDate(endTime);

                if(startDate.isBefore(CommonUtil.parserDate(dto.getStartTime()))) {
                    dto.setStartTime(time);
                }
                
                if(CommonUtil.parserDate(dto.getEndTime()).isBefore(endDate)) {
                    dto.setEndTime(endTime);
                }
            }
        }
        
        return dto;
    }
    
    
    
    
    
    public List<ProjectSummary> convertSummary(final List<Project> results){
        List<ProjectSummary> contents = new ArrayList<>();
        
        for(final Project project : results) {
            
            ProjectSummary summay = new ProjectSummary();
            
            summay.setAddress(project.getAddress());
            summay.setId(project.getId());
            summay.setName(project.getName());
            summay.setPrincipal(project.getPrincipal().getName());
            summay.setDailyWages(project.getDailyWages());
           
            final List<LogRecord> morningLogRecords = project.getMorningLogRecords();
            final List<LogRecord> afternoonLogRecords = project.getAfternoonLogRecords();
            final List<LogRecord> eveningLogRecords = project.getEveningLogRecords();
        
            if(!morningLogRecords.isEmpty()) {
                summay.setStartTime(morningLogRecords.get(0).getTime());
            }
            
            
            if(!afternoonLogRecords.isEmpty() && summay.getStartTime() != null) {
                final String time = afternoonLogRecords.get(0).getTime();
                final LocalDate startDate = CommonUtil.parserDate(time);

                if(startDate.isBefore(CommonUtil.parserDate(summay.getStartTime()))) {
                    summay.setStartTime(time);
                }
            }
            
            
            if(!eveningLogRecords.isEmpty() && summay.getStartTime() != null) {
                final String time = eveningLogRecords.get(0).getTime();
                final LocalDate startDate = CommonUtil.parserDate(time);

                if(startDate.isBefore(CommonUtil.parserDate(summay.getStartTime()))) {
                    summay.setStartTime(time);
                }
            }

            contents.add(summay);
        }
        
        return contents;
    }
}
