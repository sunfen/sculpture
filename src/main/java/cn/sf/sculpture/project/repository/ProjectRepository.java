package cn.sf.sculpture.project.repository;


import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.user.domain.entity.User;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findByUserAndStartTimeAndDeleted(User findCurrentUser, LocalDateTime startTime, Boolean deleted);

	Project findFirstByUserAndOrderByStartTimeDesc(User findCurrentUser);

	Page<Project> findByUserAndOrderByStartTimeDesc(User findCurrentUser, Pageable pageable);

	
}
