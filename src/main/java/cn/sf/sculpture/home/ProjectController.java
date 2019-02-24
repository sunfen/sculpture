package cn.sf.sculpture.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sf.sculpture.common.domain.HttpState;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.service.ProjectService;


@Controller
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("search")
	@ResponseBody
	public Page<ProjectDTO> allProjects(
			@PageableDefault(page = 0, size = 7) Pageable pageable) {
		
		List<ProjectDTO> results = new ArrayList<>();
		int size = pageable.getPageNumber() == 0 ? pageable.getPageNumber() + 1 : pageable.getPageNumber();
		for(int i = pageable.getPageNumber(); i< pageable.getPageSize()* size; i ++){
			ProjectDTO dto5 = new ProjectDTO();
			dto5.setId(Long.valueOf(i));
			dto5.setAddress("廊坊大厂" + i);
			dto5.setEndTime("2018-12-08 12:23:11");
			dto5.setName("name");
			dto5.setPrincipal(new Principal());
			dto5.setStartHour("上午");
			dto5.setStartTime("2018-07-19");
			results.add(dto5);
		}
		return new PageImpl<>(results, pageable, pageable.getPageSize() * 5);
	}



	
	@PostMapping
	@ResponseBody
	public HttpState<String> add(@RequestBody ProjectDTO project){
		
		try {
			projectService.insert(project);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("新增成功！");
	}
	
	
	@DeleteMapping("{projectId}")
	@ResponseBody
	public HttpState<String> add(@PathVariable Long projectId){
		
		try {
			projectService.deleted(projectId);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("删除成功！");
	}
	
	@GetMapping
	@ResponseBody
	public ProjectDTO add(){
		
		return projectService.findNew();
	}
	
}
