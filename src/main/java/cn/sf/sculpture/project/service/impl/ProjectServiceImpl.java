package cn.sf.sculpture.project.service.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.sf.sculpture.common.CommonUtil;
import cn.sf.sculpture.document.service.DocumentService;
import cn.sf.sculpture.project.domain.DTO;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.project.repository.ProjectRepository;
import cn.sf.sculpture.project.service.LogRecordService;
import cn.sf.sculpture.project.service.PrincipalService;
import cn.sf.sculpture.project.service.ProjectService;
import cn.sf.sculpture.project.service.WagesRecordService;
import cn.sf.sculpture.project.util.ProjectConvert;
import cn.sf.sculpture.user.domain.entity.User;
import cn.sf.sculpture.user.service.UserService;


/**
 * 登录service
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Component
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private ProjectConvert projectConvert;
    @Autowired
    private LogRecordService logRecordService;
	@Autowired
    private PrincipalService principalService;
    @Autowired
    private WagesRecordService wagesRecordService;
    @Autowired
    private DocumentService documentService;
    
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#addTotalHours(cn.sf.sculpture.project.domain.entity.Project, java.lang.Double)
     */
    @Override
    @Transactional
    public void addTotalHours(Project project, Double changeHours) {
        
        Double totalWorkHour = project.getTotalWorkHour();
        totalWorkHour = totalWorkHour == null ? 0.0 : totalWorkHour ;
        totalWorkHour = totalWorkHour + changeHours;
        project.setTotalWorkHour(totalWorkHour);
        
        BigDecimal dailyWages = project.getDailyWages().divide(new BigDecimal(8));
        BigDecimal expectTotalWages = dailyWages.multiply(new BigDecimal(project.getTotalWorkHour()));
        project.setExpectTotalWages(expectTotalWages);
        repository.save(project);
    }


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#subTotalHours(cn.sf.sculpture.project.domain.entity.Project, java.lang.Double)
     */
    @Override
    @Transactional
    public void subTotalHours(Project project, Double changeHours) {
        Double totalWorkHour = project.getTotalWorkHour();
        totalWorkHour = totalWorkHour == null ? 0.0 : totalWorkHour ;
        totalWorkHour = totalWorkHour - changeHours;
        project.setTotalWorkHour(totalWorkHour);
     
        BigDecimal dailyWages = project.getDailyWages().divide(new BigDecimal(8));
        BigDecimal expectTotalWages = dailyWages.multiply(new BigDecimal(project.getTotalWorkHour()));
        project.setExpectTotalWages(expectTotalWages);
        repository.save(project);
    }

    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#save(cn.sf.sculpture.project.domain.entity.Project)
     */
    @Override
    @Transactional
    public void save(Project project) {
        repository.save(project);
    }
    
    
    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#importer(cn.sf.sculpture.project.domain.ProjectDTO, java.util.List)
     */
    @Override
    @Transactional
    public void importer(ProjectDTO projectDTO, List<LogRecordDTO> logRecords) {
        final User user = userService.findCurrentUser();
        
        Project entity = new Project();
        entity.setUser(user);
        entity.setCreateTime(CommonUtil.getNow());
        
        Principal principal = projectDTO.getPrincipal();
  
        if(principal.getId() == null) {
            principal = principalService.insert(principal);
        }
 
        entity.setPrincipal(principal);
        entity.setName(projectDTO.getName());
        entity.setAddress(projectDTO.getAddress());
        entity.setDailyWages(projectDTO.getDailyWages());
        entity.setActualTotalWages(projectDTO.getActualTotalWages());
        entity = repository.save(entity);
        
        if(projectDTO.getActualTotalWages() != null && !projectDTO.getActualTotalWages().equals(new BigDecimal(0))) {
            
            wagesRecordService.insert(entity, projectDTO.getActualTotalWages(), projectDTO.getMethod());
        }
         
        logRecordService.inserts(logRecords, entity, user);
        
    }
    
    
    @Override
    @Transactional
    public Project insert(final ProjectDTO project) throws IOException {
        Assert.notNull(project, "project is null");
        
        final User user = userService.findCurrentUser();
        
        Project entity = null;
        
        if(project.getId() != null) {
        	
            entity = repository.getOne(project.getId());
        
        }else { 

        	entity = new Project();
        	entity.setUser(user);
        	entity.setCreateTime(CommonUtil.getNow());
        }
        
        Principal principal = project.getPrincipal();
  
        if(principal.getId() == null) {
        	principal = principalService.insert(principal);
        }
 
        entity.setAddress(project.getAddress());
        entity.setName(project.getName());
        entity.setPrincipal(principal);
        entity.setDailyWages(project.getDailyWages());
        
        double totalHour = entity.getTotalWorkHour() != null ? entity.getTotalWorkHour() : 0;
        BigDecimal dailyWages = entity.getDailyWages().divide(new BigDecimal(8));
        BigDecimal expectTotalWages = dailyWages.multiply(new BigDecimal(totalHour));
        entity.setExpectTotalWages(expectTotalWages);
       
        //删除图片
        if(project.getRemoveImages() != null && !project.getRemoveImages().isEmpty()) {
            documentService.deleteDocumentByIds(project.getRemoveImages());
        }
        
        
        return repository.save(entity);
               
    }
    
    
    
    
    @Override
    public ProjectDTO findNew() throws Exception {
    	Project project = repository.findFirstByUserOrderByCreateTimeDesc(userService.findCurrentUser());
    	if(project == null) {
    		return null;
    	}
    	return projectConvert.convertDTO(project);
    }
    
    
    @Override
    @Transactional
    public void deleted(Long projectId) {
    	Project project = repository.getOne(projectId);
    	if(project != null) {
    		repository.delete(project);
    	}
    }
    
    

    @Override
    public Project findOne(Long projectId) {
        
        return repository.getOne(projectId);
    }



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#getOne(java.lang.Long)
     */
    @Override
    public ProjectDTO getOne(Long projectId) throws Exception {
         final Project project = repository.getOne(projectId);
         return projectConvert.convertDTO(project);
    }
    
    
    
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAll()
     */
    @Override
    public List<DTO> findAll() {

        final List<Project> results = repository.findByUserOrderByCreateTimeDesc(userService.findCurrentUser());
        
        List<DTO> contents = new ArrayList<>();
        
        for(final Project project : results) {
            
            DTO summay = new DTO();
            
            summay.setId(project.getId());
            summay.setName(project.getPrincipal().getName() + " - " + project.getName());

            contents.add(summay);
        }
        
        return contents;
    }
    

    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAllByPrincipalId(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<ProjectSummary> findAllByPrincipalId(Long principalId, Pageable pageable) {
        final Page<Project> results = repository.findByUserAndPrincipalIdOrderByCreateTimeDesc(userService.findCurrentUser(), principalId, pageable);
        
        
        return new PageImpl<>(projectConvert.convertSummary(results.getContent()), pageable, results.getTotalElements());
    }
    
    
    


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#findAll(org.springframework.data.domain.Pageable, java.lang.String)
     */
    @Override
    public List<ProjectSummary> findAll(String year) {
        final List<Project> results = repository.findByYear(userService.findCurrentUser().getId(), year);
        
        return projectConvert.convertSummaries(results);
    }
    
    


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#totalWages(java.lang.String)
     */
    @Override
    public BigDecimal totalWages(String year) {
         return repository.totalWagesByYear(userService.findCurrentUser().getId(), year);
    }

    
    @Override
    public Page<ProjectSummary> findAll(Pageable pageable){
    	
    	final Page<Project> results = 
    	    repository.findByUserOrderByCreateTimeDesc(userService.findCurrentUser(), pageable);
    	
    	return new PageImpl<>(projectConvert.convertSummaries(results.getContent()), pageable, results.getTotalElements());
    }



    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#countByUser(cn.sf.sculpture.user.domain.entity.User)
     */
    @Override
    public Integer countByUser(User user) {
        return repository.countByUser(user);
    }


    /* (non-Javadoc)
     * @see cn.sf.sculpture.project.service.ProjectService#getSummary(java.lang.Long)
     */
    @Override
    public ProjectSummary getSummary(Long projectId) throws Exception {
        // TODO Auto-generated method stub
         return projectConvert.convertSummary(repository.getOne(projectId));
    }





}