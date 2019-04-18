package cn.sf.sculpture.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import cn.sf.sculpture.project.domain.DTO;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectPrincipalSummary;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.service.ProjectService;


@Controller
@RequestMapping("project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private PrincipalService principalService;
	
	@GetMapping("search")
	@ResponseBody
	public Page<ProjectSummary> allProjects(
			@PageableDefault(page = 0, size = 7) Pageable pageable) {
		
		return projectService.findAll(pageable);
	}
	
	
	@GetMapping("search/{year}")
    @ResponseBody
    public Map<String, Object> allProjects(@PathVariable String year) {
	    Map<String, Object> map = new HashMap<>();
	    
	    map.put("results", projectService.findAll(year));
	    map.put("totalWages", projectService.totalWages(year));
	    return map;
    }

    
    @GetMapping("search/principal")
    @ResponseBody
    public Page<ProjectPrincipalSummary> allPrincipals(
        @PageableDefault(page = 0, size = 7) Pageable pageable) {
        
        return principalService.findAllByPrincipal(pageable);
    }
    
    
    
    
    @GetMapping("search/principal/{principalId}")
    @ResponseBody
    public Page<ProjectSummary> getByPrincipal(
        @PathVariable Long principalId,
        @PageableDefault(page = 0, size = 7) Pageable pageable) {
        
        return projectService.findAllByPrincipalId(principalId, pageable);
    }
    
	
    @GetMapping("search/simple")
    @ResponseBody
    public List<DTO> allProjects() {
        
        return projectService.findAll();
    }
    
 
    




	/**
	 * 新增或者编辑
	 * @param project
	 * @return
	 */
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
	
	/**
     * 导入
     * @param project
     * @return
     */
    @PostMapping("import")
    @ResponseBody
    public HttpState<String> importer(@RequestBody ProjectDTO project){
        
        try {
            projectService.importer(project, project.getLogRecords());
    
        } catch (Exception e) {
            
            e.printStackTrace();
            return HttpState.error(e.getMessage());
        }
        
        return HttpState.success("新增成功！");
    }
	
	
	/**
	 * 删除
	 * @param projectId
	 * @return
	 */
	@DeleteMapping("{projectId}")
	@ResponseBody
	public HttpState<String> deleted(@PathVariable Long projectId){
		
		try {
			projectService.deleted(projectId);
		} catch (Exception e) {
			
			e.printStackTrace();
			return HttpState.error(e.getMessage());
		}
		
		return HttpState.success("删除成功！");
	}
	
	
	   
	/**
	 * 获取一个
	 * @param projectId
	 * @return
	 * @throws Exception 
	 */
    @GetMapping("{projectId}")
    @ResponseBody
    public ProjectDTO get(@PathVariable Long projectId) throws Exception{
        
        return   projectService.getOne(projectId);
    }
    
    
    /**
     * 获取一个
     * @param projectId
     * @return
     * @throws Exception 
     */
    @GetMapping("summary/{projectId}")
    @ResponseBody
    public ProjectSummary getSummary(@PathVariable Long projectId) throws Exception{
         
        
        return projectService.getSummary(projectId);
    }
	
	@GetMapping
	@ResponseBody
	public ProjectDTO findNew() throws Exception{
		
		return projectService.findNew();
	}
	
}
