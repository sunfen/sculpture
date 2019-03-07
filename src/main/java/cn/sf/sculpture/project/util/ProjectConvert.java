 package cn.sf.sculpture.project.util;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.sf.sculpture.project.domain.ProjectDTO;
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
        
        final List<LogRecord> records = project.getLogRecords();
        if(!records.isEmpty()) {
            dto.setStartTime(records.get(0).getTime());
            dto.setEndTime(records.get(records.size()-1).getTime());
        }
        return dto;
    }
}
