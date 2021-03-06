package cn.sf.sculpture.project.repository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.sf.sculpture.project.domain.entity.Project;
import cn.sf.sculpture.user.domain.entity.User;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface ProjectRepository extends JpaRepository<Project, Long>{

	Project findFirstByUserOrderByCreateTimeDesc(User findCurrentUser);

	Page<Project> findByUserOrderByCreateTimeDesc(User findCurrentUser, Pageable pageable);
	
	Page<Project> findByUserAndPrincipalIdOrderByCreateTimeDesc(User findCurrentUser, Long principalId, Pageable pageable);

    /**
     * @param findCurrentUser
     */
	List<Project> findByUserOrderByCreateTimeDesc(User findCurrentUser);

    /**
     * @param user
     * @return
     */
    Integer countByUser(User user);

    /**
     * @param id
     * @param year
     * @return
     */
    @Query(value = "select distinct p.* from project p "
        + "     left join log_record l on p.id = l.afternoon_project_id "
        + "     or p.id = l.evening_project_id or p.id = l.morning_project_id "
        + "     where p.user_id = :userId and date_format(p.create_time, '%Y') = :year  ; ", nativeQuery = true)
    List<Project> findByYear(@Param("userId") Long userId, @Param("year") String year);

    /**
     * @param id
     * @param year
     * @return
     */
    @Query(value = "select SUM(distinct p.actual_total_wages) from project p "
        + "     left join log_record l on p.id = l.afternoon_project_id "
        + "     or p.id = l.evening_project_id or p.id = l.morning_project_id "
        + "     where p.user_id = :userId and date_format(l.time, '%Y') = :year  ; ", nativeQuery = true)
    BigDecimal totalWagesByYear(@Param("userId") Long userId, @Param("year") String year);

    /**
     * @param id
     * @param year
     * @return
     */
    @Query(value = "select COUNT(l.id) as count , p.name from project p "
        + "     left join log_record l on (p.id = l.afternoon_project_id and l.afternoon_hour != 0 ) "
        + "     or ( p.id = l.evening_project_id and l.evening_hour != 0  ) "
        + "   or (p.id = l.morning_project_id  and l.morning_hour != 0 ) "
        + "     where p.user_id = :userId and date_format(l.time, '%Y') = :year   group by p.id ; ", nativeQuery = true)
    List<Map<String, Object>> countByUserIdAndYear( @Param("year") Integer year, @Param("userId") Long userId);
    
    
    /**
     * @param id
     * @param year
     * @return
     */
    @Query(value = "select SUM(distinct p.actual_total_wages) as  actualTotalWages , "
        + "      SUM(distinct p.expect_total_wages)  as expectTotalWages from project p "
        + "      left join log_record l on p.id = l.afternoon_project_id "
        + "      or p.id = l.evening_project_id or p.id = l.morning_project_id "
        + "      where  p.user_id = :userId and date_format(l.time, '%Y') = :year   ", nativeQuery = true)
    Map<String, Object> countWagesByUserIdAndYear(@Param("userId") Long userId, @Param("year") Integer year);
}
