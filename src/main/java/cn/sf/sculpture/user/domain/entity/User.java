 package cn.sf.sculpture.user.domain.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.common.domain.entity.StringDateConverter;

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
    
    
    /**
     * 修改密码验证码(前面存储验证码，后面存储发送验证码的时间，用`-`隔开)
     */
    @Column(length = 16)
    private String identifyCode;
    
    /**
     * 验证码发送时间
     */
    @Convert(converter = StringDateConverter.class)
    private String sendIdentifyCodeTime;

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

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getSendIdentifyCodeTime() {
        return sendIdentifyCodeTime;
    }

    public void setSendIdentifyCodeTime(String sendIdentifyCodeTime) {
        this.sendIdentifyCodeTime = sendIdentifyCodeTime;
    }
    
}
