 package cn.sf.sculpture.project.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sf.sculpture.project.domain.entity.Principal;
import cn.sf.sculpture.user.domain.entity.User;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface PrincipalRepository extends JpaRepository<Principal, Long>{

    /**
     * @param user
     * @param pageable
     * @return
     */
    Page<Principal> findByUser(User user, Pageable pageable);

    List<Principal> findByUser(User user);
}
