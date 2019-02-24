package cn.sf.sculpture.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.HttpState;
import cn.sf.sculpture.project.domain.WagesRecordDTO;
import cn.sf.sculpture.project.service.WagesRecordService;


@Controller
@RequestMapping("wagesrecord")
public class WagesRecordController {

	@Autowired
	private WagesRecordService wagesRecordService;
	
	@GetMapping("search/{projectId}")
	@ResponseBody
	public List<WagesRecordDTO> allProjects(@PathVariable Long projectId) {
	
		return wagesRecordService.findByProjectId(projectId);
	}



	
	@PostMapping
	@ResponseBody
	public HttpState<String> add(@RequestBody WagesRecordDTO wagesRecordDTO){
		
		try {
			wagesRecordService.insert(wagesRecordDTO);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("新增成功！");
	}
	
	
	
	@DeleteMapping("{wagesRecordId}")
	@ResponseBody
	public HttpState<String> add(@PathVariable Long wagesRecordId){
		
		try {
			wagesRecordService.deleted(wagesRecordId);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("删除成功！");
	}
	
	
}
