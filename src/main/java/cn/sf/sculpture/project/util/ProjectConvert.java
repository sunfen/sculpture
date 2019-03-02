 package cn.sf.sculpture.project.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cn.sf.sculpture.common.CommonUtil;
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
        
        final LocalDateTime startDate = CommonUtil.parserTime(project.getStartTime());
        
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setAddress(project.getAddress());
        dto.setPrincipal(project.getPrincipal());
        dto.setDailyWages(project.getDailyWages());

        dto.setStartTime(project.getStartTime());
        dto.setStartHour(TimeEnum.getName(startDate.getHour()));
        
        dto.setActualTotalWages(project.getActualTotalWages());
        dto.setExpectTotalWages(project.getExpectTotalWages());
        
        if(project.getEndTime() != null) {
            final LocalDateTime endDate = CommonUtil.parserTime(project.getEndTime());
            dto.setEndTime(project.getEndTime());
            dto.setEndHour(TimeEnum.getName(endDate.getHour()));
        }
        
        return dto;
    }
}
