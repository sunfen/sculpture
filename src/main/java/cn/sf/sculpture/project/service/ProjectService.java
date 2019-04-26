package cn.sf.sculpture.project.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.project.domain.DTO;
import cn.sf.sculpture.project.domain.LogRecordDTO;
import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.user.domain.entity.User;

public interface ProjectService {


    /**
     * @param project
     */
    void save(Project project);
    
    /**
     * @param project
     */
    void addTotalHours(Project project, Double changeHours);
    
    /**
     * @param project
     */
    void subTotalHours(Project project, Double changeHours);
    
    /**
     * @param username
     * @return
     * @throws IOException 
     * @throws Exception 
     */
    Project insert(ProjectDTO project) throws IOException;

    void deleted(Long projectId);

    /**
     * @param projectId
     * @return
     * @throws Exception 
     */
    ProjectDTO getOne(Long projectId) throws Exception;

    ProjectSummary getSummary(Long projectId) throws Exception;
    
    
	Project findOne(Long projectId);

	ProjectDTO findNew() throws Exception;

    /**
     * @param year
     * @return
     */
    List<ProjectSummary> findAll(String year);


    /**
     * @param year
     * @return
     */
    BigDecimal totalWages(String year);
    
	Page<ProjectSummary> findAll(Pageable pageable);


    /**
     * @return
     */
    List<DTO> findAll();


    /**
     * @param principalId
     * @param pageable
     * @return
     */
    Page<ProjectSummary> findAllByPrincipalId(Long principalId, Pageable pageable);

    /**
     * @param user
     * @return
     */
    Integer countByUser(User user);


    /**
     * @param project
     * @param logRecords
     */
    void importer(ProjectDTO project, List<LogRecordDTO> logRecords);

    /**
     * @param valueOf
     * @param id
     * @return
     */
    Map<String, Object> countDaysByYearAndUserId(Integer valueOf, Long id);

    /**
     * @param valueOf
     * @param id
     * @return
     */
    Map<String, Object> countWagesByYearAndUserId(Integer valueOf, Long id);

	
}
