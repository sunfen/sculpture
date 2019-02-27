 package cn.sf.sculpture.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.TimeEnum;
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
        
        LocalDateTime startDate = LocalDateTime.parse(project.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setAddress(project.getAddress());
        dto.setPrincipal(project.getPrincipal());
        dto.setDailyWages(project.getDailyWages());

        dto.setEndTime(project.getEndTime());
        dto.setStartTime(project.getStartTime());
        dto.setStartHour(TimeEnum.getName(startDate.getMonthValue()));
        
        dto.setActualTotalWages(project.getActualTotalWages());
        dto.setExpectTotalWages(project.getExpectTotalWages());
        return dto;
    }
}
