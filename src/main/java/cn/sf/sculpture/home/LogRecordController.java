package cn.sf.sculpture.home;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.HttpState;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.service.LogRecordService;


@Controller
@RequestMapping("logrecord")
public class LogRecordController {

	@Autowired
	private LogRecordService logRecordService;
	
	@GetMapping("search")
	@ResponseBody
	public Page<LogRecordDTO> allProjects(
			@PageableDefault(page = 0, size = 7) Pageable pageable) throws Exception {
	
		return logRecordService.findRecentMonth(pageable);
	}

    
    @GetMapping
    @ResponseBody
    public List<LogRecordDTO> search(Long projectId, String startime, String endtime) throws Exception{
        
        final LocalDate startDate = LocalDate.parse(startime);
        final LocalDate endDate = LocalDate.parse(endtime);

        return logRecordService.findBetween(projectId, startDate, endDate);
    }
    
    
    
   
    @GetMapping("search/{year}/{month}")
    @ResponseBody
    public List<LogRecordDTO> search(
        @PathVariable Integer year,
        @PathVariable Integer month) throws Exception{
        
        return logRecordService.findBetween(year, month);
    }

	
	@PostMapping
	@ResponseBody
	public HttpState<String> add(@RequestBody LogRecordDTO logRecordDTO){
		
		try {
			logRecordService.insert(logRecordDTO);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("新增成功！");
	}
	
	
}
