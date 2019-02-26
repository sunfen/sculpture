package cn.sf.sculpture.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.sf.sculpture.project.domain.ProjectDTO;
import cn.sf.sculpture.project.domain.ProjectSummary;
import cn.sf.sculpture.project.domain.entity.Project;

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

	void updateTotalWages(Long projectId);

    /**
     * @return
     */
    List<ProjectSummary> findAll();
 
	
}
