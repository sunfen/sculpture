package cn.sf.sculpture.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.IndexDTO;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.ProjectService;


@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private LogRecordService logRecordService;
    @Autowired
    private ProjectService projectService;
    
    
	@RequestMapping("")
	public String getText() {
		return "hello world";
	}
	
	@GetMapping("index")
	@ResponseBody
    public IndexDTO index(@PageableDefault(page = 0, size = 7) Pageable pageable) throws Exception {
	    
	    IndexDTO dto = new IndexDTO();
	    
	    dto.setPageInfo(logRecordService.findRecentMonth(pageable));
	    
	    dto.setProject(projectService.findNew());
	    
	    dto = logRecordService.getMonthWagesAndWorkDays(dto);
	    
        return dto;
    }
	    

}
