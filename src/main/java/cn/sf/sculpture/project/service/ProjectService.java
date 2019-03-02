package cn.sf.sculpture.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.user.domain.entity.User;

public interface ProjectService {


    /**
     * @param username
     * @return
     * @throws Exception 
     */
    void insert(ProjectDTO project) throws Exception;

	ProjectDTO findNew() throws Exception;

	void deleted(Long projectId);

	Page<ProjectSummary> findAll(Pageable pageable);

	Project findOne(Long projectId);

    /**
     * @return
     */
    List<ProjectSummary> findAll();

    /**
     * @param projectId
     * @return
     * @throws Exception 
     */
    ProjectDTO getOne(Long projectId) throws Exception;

    /**
     * @param project
     */
    void save(Project project);

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

 
	
}
