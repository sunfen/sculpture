 package cn.sf.sculpture.user.domain;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/13
 */
public class UserDTO {

    private String openid;
    private String nickName;
    private String avatarUrl;
    private String province;
    private String city;
    
    
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    
}
