package cn.sf.sculpture.project.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.user.domain.entity.User;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findByUserAndStartTimeAndDeleted(User findCurrentUser, String startTime, Boolean deleted);

	Project findFirstByUserAndDeletedOrderByStartTimeDesc(User findCurrentUser, Boolean deleted);

	Page<Project> findByUserAndDeletedOrderByStartTimeDesc(User findCurrentUser, Boolean deleted, Pageable pageable);

    /**
     * @param findCurrentUser
     */
	List<Project> findByUserAndDeletedOrderByStartTimeDesc(User findCurrentUser, Boolean deleted);
}
