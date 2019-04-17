package cn.sf.sculpture.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.domain.CountDTO;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.ProjectService;
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
	    

}
