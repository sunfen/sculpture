package cn.sf.sculpture.home;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.domain.CountDTO;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;


@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private LogRecordService logRecordService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    

	
	@GetMapping("index")
	@ResponseBody
    public IndexDTO home() throws Exception {
	    
	    IndexDTO dto = new IndexDTO();
	    
	    dto.setProject(projectService.findNew());
	    
	    dto = logRecordService.getMonthWagesAndWorkDays(dto);
	    
        return dto;
    }
	
	
	
	@GetMapping("my")
    @ResponseBody
    public CountDTO index() {
        
        return userService.count();
    }
	 
	
	   
    @GetMapping("statistics/{index}/{year}")
    @ResponseBody
    public Map<String, Object> statistics(
        @PathVariable("index") String index,
        @PathVariable("year") String year) {
        
        final User user = userService.findCurrentUser();
        
        switch (index) {
            case "1"://统计一年工作了多少天
                return logRecordService.countByYearAndUserId(Integer.valueOf(year), user.getId());
                
            case "2":
                return projectService.countDaysByYearAndUserId(Integer.valueOf(year), user.getId());
                
            case "3":
                return projectService.countWagesByYearAndUserId(Integer.valueOf(year), user.getId());
                
            default:
                return null;
        }
    }

}
