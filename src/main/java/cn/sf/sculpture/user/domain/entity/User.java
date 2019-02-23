 package cn.sf.sculpture.user.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Table(name="user")
@Entity
public class User extends AbstractSecureObject{

    @Column(length = 64)
    private String username;
    
    
    @Column(length = 64)
    private String password;
    
    @Column(length = 32)
    private String openid;

    /**
     * 头像地址 
     */
    @Column(length = 256)
    private String avatarUrl;
    
    @Column(length = 11)
    private String phone;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    
}
