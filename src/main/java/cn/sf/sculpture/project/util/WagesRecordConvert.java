 package cn.sf.sculpture.project.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.sf.sculpture.project.domain.MethodEnum;
import cn.sf.sculpture.project.domain.WagesRecordDTO;
import cn.sf.sculpture.project.domain.entity.WagesRecord;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/02/27
 */
@Component
public class WagesRecordConvert {

    
    public List<WagesRecordDTO> convert(List<WagesRecord> source){
        List<WagesRecordDTO> contents = new ArrayList<>();
        
        for(final WagesRecord log : source) {
            
            WagesRecordDTO summary = new WagesRecordDTO();
            contents.add(summary);
            
            summary.setProjectId(log.getProject().getId());
            summary.setCreateTime(log.getCreateTime());
            summary.setRemark(log.getRemark());
            summary.setWages(log.getWages());
            summary.setId(log.getId());
            summary.setTime(log.getTime());
            summary.setMethod(MethodEnum.getName(log.getMethod()));
        }
        return contents;
    }

}
