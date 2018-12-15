 package cn.sf.sculpture.user.repository;


import cn.sf.sculpture.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public interface UserRepository extends JpaRepository<User, Long>{

    User findByOpenid(String openid);

    /**
     * @param username
     * @return
     */
    User findByOpenidOrPhone(String openid, String phone);
}
